package com.github.open_edgn.data.format

import org.slf4j.LoggerFactory
import sun.misc.Unsafe
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.jvm.jvmName

/**
 * 解析 main 方法下的 Args 方案
 *
 * @param args Array<String> 代表Main 方法传入的启动附加参数
 * @param argsBeans  Array<KClass> 代表被注入的容器
 *
 */
@Beta
class ArgsReader(args: Array<String>, vararg argsBeans: KClass<*>) {
    private val map = HashMap<String, Any>()
    private val logger = LoggerFactory.getLogger(javaClass)

    init {
        for (bean in argsBeans) {
            if (bean.java.isInterface) {
                throw RuntimeException("无法实例化接口")
            }
            load(bean, args)
        }
    }

    private fun load(bean: KClass<*>, args: Array<String>) {
        val properties =
                bean.declaredMemberProperties.toList()

        val result = Array<Any?>(properties.size) { null }
        for ((index, property) in properties.withIndex()) {
            if (property.findAnnotation<Ignore>() != null) {
                logger.debug("忽略字段 {}.", property.name)
                continue
            }
            val argsItem = property.findAnnotation<ArgsItem>()
            try {
                if (argsItem != null) {
                    val alias = argsItem.alias.toMutableList()
                    if (alias.isEmpty()) {
                        alias.add(property.name)
                    }
                    if (argsItem.defaultValue.isNotEmpty()) {

                        logger.debug("字段 {} 指定默认值为 {},在不存在可注入的数据时将使用此默认值.", property.name, argsItem.defaultValue)
                    } else {
                        logger.debug("字段 {} 不存在默认值.", property.name)
                    }
                    injection(result, index, property.returnType.jvmErasure, args, alias, argsItem.defaultValue)
                } else {
                    logger.debug("字段 {} 不存在默认值.", property.name)
                    injection(result, index, property.returnType.jvmErasure, args, listOf(property.name), null)
                }
            } catch (e: Exception) {
                throw FormatErrorException("解析 Arg 时出现错误！[${e.message}]", e)
            }
            if (property.returnType.isMarkedNullable.not() && result[index] == null) {
                throw NullPointerException("无法初始化类型，因为字段${property.name} 为空，但此字段不允许空的数据！")
            }
        }
        val any = if (bean.isData) {
            val field = Unsafe::class.java.getDeclaredField("theUnsafe")
            field.isAccessible = true
            val unsafe = field.get(null) as Unsafe
            unsafe.allocateInstance(bean.javaObjectType)
        } else {
            bean.createInstance()
        }
        for ((index, property) in properties.withIndex()) {
            property.isAccessible = true
            property.javaField!!.set(any, result[index])
        }
        map[bean.jvmName] = any

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
                          defaultValue: String?) {
        for (item in alias) {
            val data = when {
                item.length == 1 -> {
                    loadItem(args, "-$item", type)
                }
                item.length > 1 -> {
                    loadItem(args, "--$item", type)
                }
                else -> {
                    throw IndexOutOfBoundsException("alias 长度为 0 .")
                }
            }
            if (data != null) {
                result[index] = data
                return
            }
        }

        if (defaultValue == null || defaultValue.isEmpty()) {
            throw FormatErrorException("未发现 Args 下存在可注入的数据且默认值.")
        } else {
            result[index] = StringFormatUtils.parse(defaultValue, type, true)
        }
    }

    private fun loadItem(args: Array<String>, alias: String, type: KClass<*>): Any? {
        val argsLength = args.size
        for ((index, value) in args.withIndex()) {
            if (value == alias) {
                return if (index >= argsLength) {
                    if (type.javaObjectType == Boolean::class.javaObjectType) {
                        true
                    } else {
                        break
                        // 返回指定字段的默认数值
                    }
                } else {
                    val nextItem = args[index + 1]
                    if (type.javaObjectType == Boolean::class.javaObjectType) {
                        nextItem.toUpperCase().trim() != "FALSE"
                    } else {
                        StringFormatUtils.parse(nextItem, type, true)
                    }
                }
            }
        }
        return null
    }
}


