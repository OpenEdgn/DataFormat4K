package com.github.openEdgn.dataFormat4K.args

import com.github.openEdgn.dataFormat4K.enums.DataType

/**
 * 解析 #main(args:String[]) 的接口
 *
 * 针对解析程序输入的简单接口
 *
 */
interface IArgsEdit {
    /**
     *
     * 添加一个选项模块
     *
     *  注意，key字段不会生效，需要指定 `alias` 来确定
     *
     * @param key String 键值
     * @param defaultVale T 默认数值
     * @param alias Array<out String> 代号
     * @return Boolean 是否添加成功
     */
    fun <T> addItem(key: String, defaultVale: T?, vararg alias: String): Boolean

    /**
     * 移除一个选项模块
     *
     * @param key String 键值
     * @return Boolean
     */
    fun removeItem(key: String): Boolean


    /**
     * 是否存在此字段的绑定
     * @param key String 绑定字段
     * @return Boolean
     */
    fun containsKey(key: String): Boolean

    /**
     * 是否存在此替代字段的绑定
     * @param alias String 替代字段
     * @return Boolean
     */
    fun containsAlias(alias: String): Boolean


    /**
     * 根据一个 args 来得到
     * @param args Array<String>
     * @return IArgs
     */
    fun createIArgs(args: Array<String>): IArgs

}