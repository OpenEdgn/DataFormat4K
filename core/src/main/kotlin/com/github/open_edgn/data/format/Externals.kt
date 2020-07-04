package com.github.open_edgn.data.format

import com.github.open_edgn.data.format.io.HashDataProperties
import com.github.open_edgn.data.format.io.IDataProperties
import com.github.open_edgn.data.format.utils.StringFormatUtils
import kotlin.reflect.KClass

/*
扩展方法，针对于Kotlin 语法特性
 */

/**
 * 创建一个基于 （Key/Value） 关联的数据集合包装类
 * @return IDataProperties 推荐的最佳实现
 */
fun emptyDataProperties(): IDataProperties {
    return HashDataProperties()
}

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
