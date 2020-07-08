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
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
annotation class ArgsItem(
        val defaultValue: String = "",
        vararg val alias: String
)

/**
 * 忽略字段，打上此字段后将忽略对此字段的一切事情
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION,
        AnnotationTarget.CLASS,
        AnnotationTarget.FILE,
        AnnotationTarget.PROPERTY,
        AnnotationTarget.FIELD)
annotation class Ignore()

/**
 * 标记此方法为 BETA 方法，后期可能会更改
 *
 * 更改会在发行日志下注明
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Beta()