package com.github.openEdgn.dataFormat4K.args

import com.github.openEdgn.dataFormat4K.data.DataItem
import com.github.openEdgn.dataFormat4K.enums.DataType
import com.github.openEdgn.dataFormat4K.enums.DataType.*
import com.github.openEdgn.dataFormat4K.factory.DataFormatFactory
import org.slf4j.LoggerFactory
import java.lang.Exception
import java.lang.RuntimeException
import java.lang.reflect.Field
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

/**
 *  args 解析方案
 *
 * 针对 #main(String[] args) 解析字段的解决方案，
 * 例如，`execute -c /etc/media.cfg --debug --skip`
 *
 * @constructor
 */
class ArgsFormat(vararg formatClasses: KClass<*>) {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val map = mutableMapOf<KClass<*>, Any>()
    private val systemItem: Map<String, DataItem> by lazy {
        val item: MutableMap<String, DataItem> = mutableMapOf()
        System.getProperties().forEach { t, u ->
            item[t as String] = DataItem(u.toString(), STRING)
        }
        item
    }

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

    fun loadArgs(data: Array<String>): Boolean {
        for (item in map.values) {
            if (load0(item, data)) {
                logger.debug("实体类 {} 注入完成 .", item.javaClass.simpleName)
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
            val alias = mutableListOf("-${declaredField.name.toLowerCase()}")
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
    fun <T : Any> getBeanByType(clazz: KClass<T>): T {
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
                logger.debug("将数据 [{}] 注入到类型为 {} 的字段[{}]中.", value, "Byte", declaredField.name)
                declaredField.setByte(data, value.toByte())
            }
            FLOAT -> {
                logger.debug("将数据 [{}] 注入到类型为 {} 的字段[{}]中.", value, "Float", declaredField.name)
                declaredField.setFloat(data, value.toFloat())
            }
            INTEGER -> {
                logger.debug("将数据 [{}] 注入到类型为 {} 的字段[{}]中.", value, "Integer", declaredField.name)
                declaredField.setInt(data, value.toInt())
            }
            LONG -> {
                logger.debug("将数据 [{}] 注入到类型为 {} 的字段[{}]中.", value, "Long", declaredField.name)
                declaredField.setLong(data, value.toLong())
            }
            SHORT -> {
                logger.debug("将数据 [{}] 注入到类型为 {} 的字段[{}]中.", value, "Short", declaredField.name)
                declaredField.setShort(data, value.toShort())
            }
            DOUBLE -> {
                logger.debug("将数据 [{}] 注入到类型为 {} 的字段[{}]中.", value, "Double", declaredField.name)
                declaredField.setDouble(data, value.toDouble())
            }
            BOOLEAN -> {
                logger.debug("将数据 [{}] 注入到类型为 {} 的字段[{}]中.", value, "Boolean", declaredField.name)
                declaredField.setBoolean(data, value.trim().toLowerCase() == "true")
            }
            CHAR -> {
                logger.debug("将数据 [{}] 注入到类型为 {} 的字段[{}]中.", value, "Char", declaredField.name)
                if (value.length > 1) throw ClassCastException("数据 $value 不是Char 类型")
                declaredField.setChar(data, value[0])
            }
            STRING -> {
                val formatData = DataFormatFactory.defaultValue.fill(value, systemItem, false)
                logger.debug("将数据 [{}] 注入到类型为 {} 的字段[{}]中.", formatData, "String", declaredField.name)
                declaredField.set(data, formatData)
            }
            else -> throw RuntimeException("字段不是已知可用的数据类型 ${declaredField.type.simpleName}，无法注入")
        }

    }

    private val simpleFormat: (String, List<String>) -> String = { doc: String, alias: List<String> ->
        val result = StringBuilder()
        for ((i,v) in alias.withIndex()) {
            if (i == 0){
                result.append("\t").append(v).append("\t\t").append(doc)
            }else{
                result.append("\t").append(v)
            }
            result.append("\r\n")

        }
        result.append("\r\n")
        result.toString()
    }

    /**
     * 打印帮助日志
     *
     * @param format Function2<String, List<String>, String> 帮助日志格式化工具
     * @return String 帮助日志
     */
    fun printHelp(format: (String, List<String>) -> String = simpleFormat): String {
        val builder = StringBuilder()
        builder.append("使用方法：").append("\r\n")
        map.keys.forEach {
            for (field in it.java.declaredFields) {
                val argsApis = field.getDeclaredAnnotation(ArgsApis::class.java)
                val argsField = field.getDeclaredAnnotation(ArgsField::class.java)
                var argApi: ArgApi? = null
                if (argsApis != null) {
                    argsApis.api.forEach { item ->
                        val case = item.locale.trim().toLowerCase()
                        if (case == Locale.getDefault().toString()) {
                            argApi = item
                        }
                    }
                    if (argApi == null) {
                        argApi = argsApis.api[0]
                    }

                }
                if (argsField != null && argApi != null) {
                    builder.append(format(argApi!!.doc, argsField.alias.asList()))
                } else if (argsField != null && argApi == null) {
                    builder.append(format("", argsField.alias.asList()))
                } else {
                    builder.append(format("", listOf("-" + field.name.toLowerCase())))
                }
            }
        }
        return builder.toString()
    }

}