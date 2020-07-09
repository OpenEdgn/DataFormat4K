package com.github.open_edgn.data.format.utils

import java.io.InputStream
import java.io.Reader
import java.nio.charset.Charset


/**
 * 基础的字符串工具类
 */
object StringUtils {
    /**
     * 读取 InputStream 下的所有字符串
     *
     * @param inputStream InputStream
     * @param charset Charset
     * @return String
     */
    fun readText(inputStream: InputStream, charset: Charset = Charsets.UTF_8): String {
        val result = StringBuilder()
        try {
            val bytes = inputStream.readBytes()
            result.append(bytes.toString(charset))
        } catch (e: Exception) {
            inputStream.close()
            throw e
        } finally {
            inputStream.close()
        }
        return result.toString()
    }

    fun readText(reader: Reader): String {
        return reader.readText()
    }

    fun checkNullOrEmpty(data: CharSequence?): Boolean {
        val d = data?:return false
        return d.isEmpty()
    }

    fun checkNullOrBlank(data: CharSequence?): Boolean {
        val d = data?:return false
        return d.isBlank()
    }
}