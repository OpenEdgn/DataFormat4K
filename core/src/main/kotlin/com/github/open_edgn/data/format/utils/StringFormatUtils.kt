package com.github.open_edgn.data.format.utils

import kotlin.reflect.KClass


/**
 * 字符串格式化工具
 *
 * 将字符串转换成你想要的类型
 *
 */
object StringFormatUtils {

    private val formatMap: HashMap<Class<*>, IObjectFormat<*>> = HashMap()

    init {
        include(
                ByteObjectFormat(),
                ShortObjectFormat(),
                IntegerObjectFormat(),
                LongObjectFormat(),
                FloatObjectFormat(),
                DoubleObjectFormat(),
                BooleanObjectFormat(),
                CharObjectFormat(),
                StringObjectFormat(),
                FileObjectFormat()
        )
        // 一些默认的方案，更多的考虑以后再加
    }

    /**
     * 导入解析方案
     *
     * 默认会覆盖原始的解析方案，请注意
     *
     * @param formatArr Array<out IObjectFormat<*>> 解析方案
     */
    fun include(vararg formatArr: IObjectFormat<*>) {
        for (format in formatArr) {
            for (clazz in format.register) {
                formatMap[clazz] = format
            }
        }
    }

    /**
     * 扩展方法，具体查看 `#parse(String,Class)`
     */
    fun <T : Any> parse(source: String, clazz: KClass<T>): T {
        return parse(source, clazz.javaObjectType)
    }

    /**
     *  将字符串格式化成对应的数据
     *
     * @param source String  原始的字符串数据
     * @param clazz Class<T> 想要转换的对象
     * @return T 得到的对象
     * @throws FormatErrorException 解析识别，原始的字符串存在错误导致无法解析
     * @throws NotFoundException 未找到对应的解析方案
     */
    @Suppress("UNCHECKED_CAST")
    @Throws(FormatErrorException::class, NotFoundException::class)
    fun <T : Any> parse(source: String, clazz: Class<T>): T {
        var parse = formatMap[clazz]
        if (parse == null) {
            for (key in formatMap.keys) {
                if (key.isAssignableFrom(clazz)) {
                    parse = formatMap[key]
                    break
                }
            }
        }
        if (parse == null) {
            throw NotFoundException("无法找到解析 [ ${clazz.simpleName} ] 的方案.")
        } else {
            return try {
                parse.parse(source) as T
            } catch (e: Throwable) {
                throw FormatErrorException(e.message, e)
            }
        }
    }

    /**
     *  扩展方法，具体查看 `#format(T,Class)`
     */
    fun <T : Any> format(source: T, clazz: KClass<out T>): String {
        return format(source, clazz.javaObjectType)
    }
    /**
     *  扩展方法，具体查看 `#format(T,Class)`
     */
    fun <T : Any> format(source: T): String {
        return format(source, source::class.java)
    }

    /**
     * 将数据转换成对应的字符串类型
     *
     * @param source T 原始数据
     * @param clazz Class<T> 数据的Class 对象
     * @return String 对应的字符串对象
     */
    fun <T> format(source: T, clazz: Class<out T>): String {
        var format = formatMap[clazz]
        if (format == null) {
            for (key in formatMap.keys) {
                if (key.isAssignableFrom(clazz)) {
                    format = formatMap[key]
                    break
                }
            }
        }
        if (format == null) {
            throw NotFoundException("无法找到解析 [ ${clazz.simpleName} ] 的方案.")
        } else {
            return try {
                format.format(source as Any)
            } catch (e: Throwable) {
                throw FormatErrorException(e.message, e)
            }
        }
    }
}

