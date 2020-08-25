package com.github.open_edgn.data.format

import java.io.InputStream
import java.io.Reader
import java.nio.charset.Charset
import kotlin.reflect.KClass

/*
扩展方法，针对于Kotlin 语法特性
 */
//############# HashDataProperties #################

/**
 * 创建一个基于 （Key/Value） 关联的数据集合包装类
 *
 * @return IDataProperties 推荐的最佳实现
 */
fun emptyDataProperties(): IDataProperties {
    return HashDataProperties()
}

//############# HashDataProperties #################
//############# StringFormatUtils #################

/**
 * 将一个字符串序列化成你想要的字段
 *
 * @receiver String 原始字符串
 * @param clazz KClass<K> 目标字段
 * @return K 目标类
 */
fun <K : Any> String.parse(clazz: KClass<K>): K {
    return StringFormatUtils.parse(this, clazz)
}

/**
 *  将可用的类格式化成字符串类型 （并非通用！）
 *
 * @receiver Any 原始类型
 * @return String 格式化后的字符串
 */
fun Any.format(): String {
    return StringFormatUtils.format(this)
}

//############# StringFormatUtils #################
//############# StringFillUtils #################
/**
 *  将预设的占位符替换成目标字段
 *
 * @receiver String 原始字符串
 * @return String 新的字符串
 */
fun String.fillSystemProperties(): String {
    return StringFillUtils.fillFromSystemProp(this)
}

//############# StringFillUtils #################
//############# ObjectUtils #################
/**
 * 判断此对象是否为 NULL
 * @receiver Any? 目标对象
 * @return Boolean 结果
 */
fun Any?.isNull(): Boolean {
    return ObjectUtils.isNull(this)
}
//############# ObjectUtils #################
//############# StringUtils #################
/**
 * 判断此字符串是否为 NULL 或者为空
 *
 * @receiver String? 目标字符串
 * @return Boolean 结果
 */
fun String?.isNullOrEmpty(): Boolean {
    return StringUtils.isNullOrEmpty(this)
}

/**
 * 判断此字符串是否为 NULL 或者为空
 *
 * @receiver String? 目标字符串
 * @return Boolean 结果
 */
fun String?.isNullOrBlank(): Boolean {
    return StringUtils.isNullOrBlank(this)
}

/**
 * 从 InputStream 下读取全部数据并将其转换成字符串
 *
 * @receiver InputStream 流
 * @param charset Charset 编码类型
 * @return String 得到的字符串
 */
fun InputStream.readText(charset: Charset = Charsets.UTF_8): String {
    return StringUtils.readText(this, charset)
}

/**
 * 从 Reader 下读取全部字符
 *
 * @receiver Reader 目标字符流
 * @return String 得到的字符串
 */
fun Reader.readText(): String {
    return StringUtils.readText(this)
}

/**
 *
 * 从异常中读取抛出错误的全部信息
 *
 * @receiver Throwable 异常
 * @return String 全部信息
 */
@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
fun Throwable.readPrintText(): String {
    return StringUtils.throwableFormat(this)
}
//############# StringUtils #################

