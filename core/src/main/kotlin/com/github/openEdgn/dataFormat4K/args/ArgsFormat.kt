package com.github.openEdgn.dataFormat4K.args

import org.slf4j.LoggerFactory
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

    fun load(data: Array<String>) {
        for (item in map.values) {
            load0(item, data)
        }
    }

    private fun load0(item: Any, data: Array<String>) {
        val clazz = item.javaClass
        for (declaredField in clazz.declaredFields) {
            if (declaredField.getDeclaredAnnotation(ArgsIgnore::class.java) != null) {
                // 此字段忽略处理
                continue
            }
            val alias = mutableListOf("--${clazz.simpleName}-${declaredField.name}")
            declaredField.getDeclaredAnnotation(ArgsField::class.java)?.let {
                alias.clear()
                alias.addAll(it.alias)
            }
            val dataSize = data.size
            for ((index, value) in data.withIndex()) {
                for (key in alias) {
                    if (key == value) {

                    }
                }
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