package com.github.open_edgn.data.format


/**
 * 标记此字段为数据绑定的字段
 *
 * 此字段可以忽略，同样会扫描，但会按照默认样式来绑定
 *
 * @property defaultValue String 默认值
 * @property alias Array<out String>
 * @constructor
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION,AnnotationTarget.PROPERTY,AnnotationTarget.FIELD)
annotation class ArgsItem(
        val defaultValue:String = "",
        vararg val alias: String
)