package com.github.open_edgn.data.format

import java.text.SimpleDateFormat

/**
 * 内部日志模块，不提供外部访问
 */
internal object InternalLogger {
     enum class Type(val level: Int) {
         TRACE(0),
         DEBUG(1),
         INFO(2),
         WARN(3),
         ERROR(4),
     }

    /**
     * 判断是否开启DEBUG
     */
    private val isDebug: Boolean by lazy {
        System.getProperty("dataFormat4K.debug", "FALSE").toUpperCase() == "TRUE"
    }
    private val dateFormat = SimpleDateFormat("MM/dd HH:mm:ss")
    fun printLogger(clazz: Class<*>, type: Type, message: String, e: Throwable? = null) {
        if ((type.level < Type.INFO.level && isDebug) || type.level > Type.INFO.level) {
            if (e != null) {
                System.out.printf("[%s][%s] - %s:%s\r\n%s\r\n", dateFormat.format(System.currentTimeMillis()),
                        type.name, clazz.simpleName, message, e.readPrintText())
            } else {
                System.out.printf("[%s][%s] - %s:%s\r\n", dateFormat.format(System.currentTimeMillis()),
                        type.name, clazz.simpleName, message)
            }
        }
    }
}