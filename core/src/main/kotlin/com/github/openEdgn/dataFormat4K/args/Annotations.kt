package com.github.openEdgn.dataFormat4K.args


/**
 * 标记此字段为数据绑定的字段
 *
 * 此字段可以忽略，同样会扫描，但会按照默认样式来绑定
 *
 * @property alias Array<out String> 字段其他代号 ，如不表示则为 ["--" + 字段名称]
 * @constructor
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class ArgsField(
         vararg val alias: String = []
)

/**
 * 忽略此字段
 *
 * 注意，此标记优先于 `@ArgsField` ,一旦此标记存在，那么`@ArgsField` 字段将不会生效
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class ArgsIgnore()

