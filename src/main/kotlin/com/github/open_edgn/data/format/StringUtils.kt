package com.github.open_edgn.data.format

import java.io.*
import java.nio.charset.Charset


/**
 * 基础的字符串工具类
 */
object StringUtils {
    /**
     * 读取 InputStream 下的所有字符串
     *
     * @param inputStream InputStream 原始流
     * @param charset Charset 字符串编码类型
     * @return String 读取到的全部字符串
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

    /**
     * 从 Reader 读取全部字符串
     * @param reader Reader 原始 Reader 流
     * @return String 读取到的全部字符串
     */
    fun readText(reader: Reader): String {
        val stringWriter = StringWriter()
        reader.copyTo(stringWriter)
        return stringWriter.toString()
    }


    /**
     * 测试字符串是否为 NULL 或者为空的
     * @param data CharSequence? 字符串
     * @return Boolean 结果
     */
    fun isNullOrEmpty(data: CharSequence?): Boolean {
        val d = data ?: return true
        return d.isEmpty()
    }

    /**
     * 测试字符串是否为 NULL 或者为空白字符
     * @param data CharSequence? 字符串
     * @return Boolean 结果
     */
    fun isNullOrBlank(data: CharSequence?): Boolean {
        val d = data ?: return true
        return d.isBlank()
    }


    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    internal fun privateThrowableFormat(throws: java.lang.Throwable): String {
        val outputStream = ByteArrayOutputStream()
        val printWriter = PrintWriter(OutputStreamWriter(outputStream, Charsets.UTF_8), true)
        throws.printStackTrace(printWriter)
        printWriter.flush()
        val array = outputStream.toByteArray()
        printWriter.close()
        outputStream.close()
        return array.toString(Charsets.UTF_8).trim()
    }

    /**
     * 对抛出的异常信息全部捕获
     * @param throws Throwable 异常信息
     * @return String 捕获的日志
     */
    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    fun throwableFormat(throws: Throwable) = privateThrowableFormat(throws as java.lang.Throwable)
}