package com.github.open_edgn.data.format


/**
 * 标记此字段为数据绑定的字段
 *
 * 此字段可以忽略，同样会扫描，但会按照默认样式来绑定
 *
 * @property defaultValue String 默认值
 * @param nullable Boolean 指定此字段是否可以为 null
 * @property alias Array<out String>
 * @constructor
 */
@Deprecated("将在后期版本移除")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
annotation class ArgsItem(
        val defaultValue: String = "",
        val nullable: Boolean = true,
        vararg val alias: String
)

/**
 * 忽略字段，打上此字段后将忽略对此字段的一切事情
 */
@Deprecated("将在后期版本移除")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION,
        AnnotationTarget.CLASS,
        AnnotationTarget.FILE,
        AnnotationTarget.PROPERTY,
        AnnotationTarget.FIELD)
annotation class Ignore
