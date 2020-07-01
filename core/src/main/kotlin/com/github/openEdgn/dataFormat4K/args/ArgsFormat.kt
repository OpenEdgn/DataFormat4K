package com.github.openEdgn.dataFormat4K.args

import com.github.openEdgn.dataFormat4K.enums.DataType
import com.github.openEdgn.dataFormat4K.enums.DataType.*
import org.slf4j.LoggerFactory
import java.lang.Exception
import java.lang.RuntimeException
import java.lang.reflect.Field
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class ArgsFormat(vararg formatClasses: KClass<*>) {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val map = mutableMapOf<KClass<*>, Any>()

    init {
        if (formatClasses.isEmpty()) {
            throw IndexOutOfBoundsException("实体类为空")
        }
        for (formatClass in formatClasses) {
            // 预先创建存放容器
            map[formatClass] = formatClass.createInstance()
        }
        logger.debug("实体类已注册，共存在 {} 个,分别为 [{}] .", map.size, map.values.joinToString { it.javaClass.simpleName })
    }

    fun load(data: Array<String>): Boolean {
        for (item in map.values) {
            if (load0(item, data)) {
                logger.debug("实体类 {} 已读取成功。", item.javaClass.simpleName)
            } else {
                logger.debug("读取中止，args 未包含必须字段！")
                return false
            }
        }
        return true
    }

    private fun load0(item: Any, data: Array<String>): Boolean {
        val clazz = item.javaClass
        for (declaredField in clazz.declaredFields) {
            if (declaredField.getDeclaredAnnotation(ArgsIgnore::class.java) != null ||
                    DataType.formatClass(declaredField.type) == UNKNOWN) {
                logger.debug("字段 {} 已标注“@ArgsIgnore“ 注解，或者{}不为基础数据类型 .",
                        declaredField.name, declaredField.type.simpleName)
                continue
            }
            declaredField.isAccessible = true
            val alias = mutableListOf("--${clazz.simpleName}-${declaredField.name}")
            var requiredField = false
            //表示必须要此字段
            declaredField.getDeclaredAnnotation(ArgsField::class.java)?.let {
                alias.clear()
                alias.addAll(it.alias)
                requiredField = it.ignorable
            }
            for (key in alias) {
                for ((index, value) in data.withIndex()) {
                    if (key == value) {
                        if (fillData(item, declaredField, data, index)) {
                            requiredField = false
                            break
                        }
                    }
                }
            }
            if (requiredField) {
                return false
            }
        }
        return true
    }

    private fun fillData(data: Any, declaredField: Field, dataStr: Array<String>, index: Int): Boolean {

        val dataType = DataType.formatClass(declaredField.type)
        if ((dataStr.size - 1) == index && dataType != BOOLEAN) {
            logger.warn("无法解析字段！因为此 alias 在 args 下已处于末尾！")
            return false
        }
        val nextDataStr = dataStr[index + 1]
        when (dataType) {
            STRING, CHAR, BYTE, FLOAT, INTEGER, LONG, SHORT, DOUBLE -> {
                try {
                    injection(data, declaredField, nextDataStr)
                    return true
                } catch (e: Exception) {
                    throw NumberFormatException(e.message)
                }
            }
            BOOLEAN -> {
                if ((dataStr.size - 1) == index) {
                    injection(data, declaredField, "true")
                } else {
                    injection(data, declaredField, "${nextDataStr.trim().toLowerCase() == "true"}")
                }
                return true
            }
            UNKNOWN -> {
                logger.warn("无法处理字段 {}，因为他不是一个基础数据类型 {}。", declaredField.name
                        , declaredField.type.simpleName)
                return false
            }
        }
    }

    /**
     * 得到解析完成的实体，注意，如果不存在将直接抛出异常
     * 并且将返回原始引用
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> format(clazz: KClass<T>): T {
        val data = map[clazz] ?: throw NullPointerException("未找到类 ${clazz.javaObjectType.name} ")
        return data as T
    }

    /**
     * 强制数据注入
     *
     * 强制注入数据，如果无法注入则抛出异常
     *
     * @param data Any 被注入的对象
     * @param declaredField Field 对象字段
     * @param value String 对象数据格式化的字符串
     */
    private fun injection(data: Any, declaredField: Field, value: String) {
        declaredField.isAccessible = true
        when (DataType.formatClass(declaredField.type)) {
            BYTE -> {
                logger.debug("字段类型为 Byte 类型，参数将数据 $value 注入到 ${declaredField.name} 下 ...")
                declaredField.setByte(data, value.toByte())
            }
            FLOAT -> {
                logger.debug("字段类型为 Float 类型，参数将数据 $value 注入到 ${declaredField.name} 下 ...")
                declaredField.setFloat(data, value.toFloat())
            }
            INTEGER -> {
                logger.debug("字段类型为 Integer 类型，参数将数据 $value 注入到 ${declaredField.name} 下 ...")
                declaredField.setInt(data, value.toInt())
            }
            LONG -> {
                logger.debug("字段类型为 Long 类型，参数将数据 $value 注入到 ${declaredField.name} 下 ...")
                declaredField.setLong(data, value.toLong())
            }
            SHORT -> {
                logger.debug("字段类型为 Short 类型，参数将数据 $value 注入到 ${declaredField.name} 下 ...")
                declaredField.setShort(data, value.toShort())
            }
            DOUBLE -> {
                logger.debug("字段类型为 Double 类型，参数将数据 $value 注入到 ${declaredField.name} 下 ...")
                declaredField.setDouble(data, value.toDouble())
            }
            BOOLEAN -> {
                logger.debug("字段类型为 Bool 类型，参数将数据 $value 注入到 ${declaredField.name} 下 ...")
                declaredField.setBoolean(data, value.trim().toLowerCase() == "true")
            }
            CHAR -> {
                logger.debug("字段类型为 Char 类型，参数将数据 $value 注入到 ${declaredField.name} 下 ...")
                if (value.length > 1) throw ClassCastException("数据 $value 不是Char 类型")
                declaredField.setChar(data, value[0])
            }
            STRING -> {
                logger.debug("字段类型为 String 类型，参数将数据 $value 注入到 ${declaredField.name} 下 ...")
                declaredField.set(data, value)
            }
            else -> throw RuntimeException("字段不是已知可用的数据类型 ${declaredField.type.simpleName}，无法注入")
        }
    }


}