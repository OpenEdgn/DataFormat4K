package com.github.open_edgn.data.format.utils

import com.github.open_edgn.data.format.ArgsItem
import com.github.open_edgn.data.format.Beta
import com.github.open_edgn.data.format.FormatErrorException
import org.slf4j.LoggerFactory
import java.lang.RuntimeException
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.*

/**
 * 解析 main 方法下的 Args 方案
 *
 * @param args Array<String> 代表Main 方法传入的启动附加参数
 * @param argsBeans  Array<KClass> 代表被注入的容器
 *
 */
@Beta
class ArgsReader (args: Array<String>, vararg argsBeans: KClass<*>) {
    private val map = HashMap<String, Any>()

    init {
        for (bean in argsBeans) {
            if (bean.java.isInterface) {
                throw RuntimeException("无法实例化接口")
            }
            load(bean, args)
        }
    }

    private fun load(bean: KClass<*>, args: Array<String>) {
        val properties = bean.declaredMemberProperties.toList()
        val result = Array<Any?>(properties.size) { null }
        for ((index, property) in properties.withIndex()) {
            val argsItem = property.findAnnotation<ArgsItem>()
            try {
                if (argsItem != null) {
                    val alias = argsItem.alias.toMutableList()
                    if (alias.isEmpty()) {
                        alias.add(property.name)
                    }
                    injection(result, index, property.returnType.jvmErasure, args, alias, argsItem.defaultValue)
                } else {
                    injection(result, index, property.returnType.jvmErasure, args, listOf(property.name), "")
                }
            } catch (e: Exception) {
                throw FormatErrorException("解析 Arg 时出现错误！[${e.message}]", e)
            }
            if (property.returnType.isMarkedNullable.not() && result[index] == null) {
                throw NullPointerException("无法初始化类型，因为字段${property.name} 为空，但此字段不允许空的数据！")
            }
        }
        if (bean.isData) {
            map[bean.jvmName] =
            bean.javaObjectType.declaredConstructors[0].newInstance(*result)
        } else {
            val any = bean.createInstance()
            for ((index, property) in properties.withIndex()) {
                property.isAccessible = true
                property.javaField!!.set(any, result[index])
            }
            map[bean.jvmName] = any
        }
    }

    /**
     * 得到 args 的实例化注入的对象
     *
     * @param kClass KClass<T> 对象 Class
     * @return T 实例化的对象
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getArgsBean(kClass: KClass<T>): T {
        val result = map[kClass.jvmName]
        if (result == null) {
            throw NullPointerException("未找到 ${kClass.jvmName} 的实例化对象.")
        } else {
            return result as T
        }
    }

    private fun injection(result: Array<Any?>,
                          index: Int,
                          type: KClass<*>,
                          args: Array<String>,
                          alias: List<String>,
                          defaultValue: String) {
        for (item in alias) {
            result[index] = when {
                item.length == 1 -> {
                    loadItem(args, "-$item", type, defaultValue)
                }
                item.length > 1 -> {
                    loadItem(args, "--$item", type, defaultValue)
                }
                else -> {
                    throw IndexOutOfBoundsException("alias 长度为 0 .")
                }
            }
        }
    }

    private fun loadItem(args: Array<String>, alias: String, type: KClass<*>, defaultValue: String): Any? {
        val argsLength = args.size
        for ((index, value) in args.withIndex()) {
            if (value == alias) {
                return if (index >= argsLength) {
                    if (type.javaObjectType == Boolean::class.javaObjectType) {
                        true
                    } else {
                        break
                    }
                } else {
                    val nextItem = args[index + 1]
                    if (type.javaObjectType == Boolean::class.javaObjectType) {
                        nextItem.toUpperCase().trim() != "FALSE"
                    } else {
                        StringFormatUtils.parse(nextItem, type,true)
                    }
                }
            }
        }
        return StringFormatUtils.parse(defaultValue, type,true)
    }
}


