package com.github.open_edgn.data.format

import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.jvmErasure

/**
 * 新的 args 解析方案
 *
 * @constructor
 * @param allowJVMArgs 添加 JVM 参数
 * @param args 程序传参
 */
@Deprecated("实用性不大，将在后期版本移除")
class Args2Reader(args: Array<String>, allowJVMArgs: Boolean = false) : BaseArgsLoader() {
    private val data = HashMap<String, String>()

    init {
        for (arg in args) {
            val pair = if (arg.contains(Regex("^--D\\S*=\\S*$")) && allowJVMArgs) {
                val decode = decode(arg.substring(3))
                decode?.run { System.setProperty(first, StringFillUtils.fillFromSystemProp(second)) }
                decode
            } else if (arg.contains(Regex("^--\\S*=\\S*$"))) {
                decode(arg.substring(2))
            } else {
                null
            }
            pair?.let {
                data[it.first] = StringFillUtils.fill(StringFillUtils.fillFromSystemProp(it.second), data)
            }
        }
        InternalLogger.printLogger(
                javaClass,
                InternalLogger.Type.DEBUG,
                "data:${data}"
        )
    }

    private fun decode(arg: String): Pair<String, String>? {
        val split = arg.split(Regex("="), 2)
        if (split.size < 2) {
            return null
        }
        return Pair(split[0], split[1])
    }


    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> getArgsBean(kClass: KClass<T>): T {
        val obj = ObjectUtils.createNewObject<Any>(kClass, true)
        val params = kClass.declaredMemberProperties.toList()
        for (param in params) {
            val argsAnnotation = param.findAnnotation<ArgsItem>()
            val ignoreAnnotation = param.findAnnotation<Ignore>()
            if (argsAnnotation == null || ignoreAnnotation != null) {
                InternalLogger.printLogger(javaClass, InternalLogger.Type.DEBUG, "字段 ${param.name} 已忽略 .")
                //无法判断此字段对应的键值对
                continue
            }
            val canNullable = param.returnType.isMarkedNullable && argsAnnotation.nullable
            var value: String? = argsAnnotation.alias.let {
                it.forEach { item ->
                    val data = data[item]
                    if (data != null) return@let data
                }
                null
            }
            if (value == null && !canNullable && argsAnnotation.defaultValue == "") {
                //未找到可用的数据
                InternalLogger.printLogger(javaClass, InternalLogger.Type.DEBUG, "字段 ${param.name} 不存在映射且未找到默认可选值.")
                throw NotFoundException("")
            }
            if (value == null) {
                value = argsAnnotation.defaultValue
            }
            val valueObj = StringFormatUtils.parse(StringFillUtils.fill(value, data), param.returnType.jvmErasure, true)
            val javaField = param.javaField!!
            javaField.isAccessible = true
            javaField.set(obj, valueObj)
            InternalLogger.printLogger(javaClass, InternalLogger.Type.DEBUG, "为字段 ${param.name} 注入数据 $valueObj.")
        }
        return obj as T
    }

}