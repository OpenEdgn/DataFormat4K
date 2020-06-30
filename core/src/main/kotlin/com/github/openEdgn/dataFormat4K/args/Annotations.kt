package com.github.openEdgn.dataFormat4K.args


/**
 * 标记此字段为数据绑定的字段
 *
 * @property key String 字段代号，默认为 ["类名称" + "." + "字段名称"]
 * @property alias Array<out String> 字段其他代号 ，如不表示则为 ["--" + "类名称" + "-" + "字段名称"]
 * @constructor
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class ArgsField(
        val key: String = "", vararg val alias: String = []
)

/**
 * 忽略此字段
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class ArgsIgnore()