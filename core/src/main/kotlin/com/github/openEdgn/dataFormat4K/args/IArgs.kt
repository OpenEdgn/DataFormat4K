package com.github.openEdgn.dataFormat4K.args

import com.github.openEdgn.dataFormat4K.enums.DataType

/**
 * 解析 #main(args:String[]) 的接口
 *
 * 针对解析程序输入的简单接口
 *
 */
interface IArgs {
    /**
     *
     * 添加一个选项模块
     *
     * @param key String 键值
     * @param formatType DataType 数据格式化类型
     * @param defaultVale Any 默认数值
     * @param alias Array<out String> 代号
     * @return Boolean 是否添加成功
     */
    fun addItem(key: String, formatType: DataType, defaultVale: Any, vararg alias: String): Boolean

    /**
     * 移除一个选项模块
     *
     * @param key String 键值
     * @return Boolean
     */
    fun removeItem(key: String):Boolean
}