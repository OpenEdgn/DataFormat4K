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
@Target(AnnotationTarget.FIELD,AnnotationTarget.FUNCTION)
annotation class ArgsField(
        val defaultValue:String = "",
        vararg val alias: String = []
)

//
///**
// * 文档字段，在此标记可当作文档
// *
// * @property api Array<out ArgApi> 文档字段
// * @constructor
// */
//@Retention(AnnotationRetention.RUNTIME)
//@Target(AnnotationTarget.FIELD)
//annotation class ArgsApis(vararg val api: ArgApi)
//
//
///**
// * 文档多语言指示器
// *
// * @property locale String 语言代号 ，从 `java.util.Locale#toString()` 下获取语言代号
// * @property doc String 使用相关语言的文档
// * @constructor
// */
//@Retention(AnnotationRetention.RUNTIME)
//annotation class ArgApi(val locale: String, val doc: String)
//
