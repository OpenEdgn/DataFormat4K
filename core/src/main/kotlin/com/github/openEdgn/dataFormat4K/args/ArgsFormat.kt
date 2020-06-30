package com.github.openEdgn.dataFormat4K.args

import com.github.openEdgn.dataFormat4K.enums.DataType
import com.github.openEdgn.dataFormat4K.enums.DataType.*
import org.slf4j.LoggerFactory
import java.lang.Exception
import java.lang.reflect.Field
import kotlin.math.log
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class ArgsFormat(vararg formatClasses: KClass<*>) {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val map = mutableMapOf<KClass<*>, Any>()

    init {
        if (formatClasses.isEmpty()) {
            throw IndexOutOfBoundsException("包装类为空")
        }
        logger.debug("包装类已注册，共存在 {} 个,分别为 {} .", map.size, map.values.joinToString { it.javaClass.name })
        for (formatClass in formatClasses) {
            // 预先创建存放容器
            map[formatClass] = formatClass.createInstance()
        }
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
                logger.debug("字段 {} 已标注“@ArgsIgnore“ 注解，或者不为基础数据类型 {}.",
                        declaredField.name,declaredField.type.simpleName)
                continue
            }
            declaredField.isAccessible = true
            val alias = mutableListOf("--${clazz.simpleName}-${declaredField.name}")
            var ignorable = true
            declaredField.getDeclaredAnnotation(ArgsField::class.java)?.let {
                alias.clear()
                alias.addAll(it.alias)
                ignorable = it.ignorable
            }
            for (key in alias) {
                for ((index, value) in data.withIndex()) {
                    if (key == value) {
                        if (fillData(item,declaredField,data,index)){
                            break
                        }
                    }
                }
            }
            if (!ignorable){

            }
        }
    }

    private fun fillData(data: Any, declaredField: Field, dataStr: Array<String>, index: Int): Boolean {

        val dataType = DataType.formatClass(declaredField.type)
        if ((dataStr.size -1) == index && dataType != BOOLEAN){
            logger.warn("无法解析字段！因为此 alias 在 args 下已处于末尾！")
            return false
        }
        val nextDataStr = dataStr[index +1]
        when (dataType) {
            STRING,CHAR,BYTE,FLOAT,INTEGER,LONG,SHORT,DOUBLE  ->{
                try {

                }catch (e:Exception){

                }
            }
            BOOLEAN -> {
                if ((dataStr.size -1) == index ){
                    declaredField.set(data,true)
                }else{
                    declaredField.set(data,nextDataStr.trim().toLowerCase() == "true")
                }
            }
            UNKNOWN -> {
                logger.warn("无法处理字段 {}，因为他不是一个基础数据类型 {}。",declaredField.name
                        ,declaredField.type.simpleName)
                return false
            }
        }

    }


    /**
     * 得到解析完成的实体，注意，如果不存在将直接抛出异常
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> format(clazz: KClass<T>): T {
        return map[clazz]!! as T
    }
}