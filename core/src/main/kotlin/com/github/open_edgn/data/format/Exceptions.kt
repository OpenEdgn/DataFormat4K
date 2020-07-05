package com.github.open_edgn.data.format

import java.lang.RuntimeException


/**
 *  通用异常：未找到的异常
 * @property message String? 异常消息，必须
 */
class NotFoundException (override val message: String?): RuntimeException(message)

/**
 *  通用异常：格式化错误异常
 * @property message String? 异常消息，必须
 */
class FormatErrorException(override val message: String?, e: Throwable? = null): RuntimeException(message,e)
