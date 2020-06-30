package com.github.openEdgn.dataFormat4K.args



/**
 * 标记此字段为数据绑定的字段
 *
 * 此字段可以忽略，同样会扫描，但会按照默认样式来绑定
 *
 * @property alias Array<out String> 字段其他代号 ，如不表示则为 ["--" + 字段名称]
 * @property ignorable Boolean 此字段是否为强制的，如果为 true 则表示传入的args 必须带上此字段
 * @constructor
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class ArgsField(
        vararg val alias: String = [],
        val ignorable: Boolean = false
)

/**
 * 忽略此字段
 *
 * 注意，此标记优先于 `@ArgsField` ,一旦此标记存在，那么`@ArgsField` 字段将不会生效
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class ArgsIgnore()


/**
 * 文档字段，在此标记可当作文档
 *
 * @property api Array<out ArgApi> 文档字段
 * @constructor
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class ArgsApis(vararg val api: ArgApi)


/**
 * 文档多语言指示器
 *
 * @property locale String 语言代号 ，从 `java.util.Locale#toString()` 下获取语言代号
 * @property doc String 使用相关语言的文档
 * @constructor
 */
@Retention(AnnotationRetention.RUNTIME)
annotation class ArgApi(val locale: String, val doc: String)

