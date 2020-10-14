package com.github.open_edgn.data.format

interface IObjectFormat<T : Any> {
    /**
     * 注册可解析的类
     */
    val register: Array<Class<out T>>

    /**
     * 将字符串解析成需要的类型
     *
     * @param data String 原始字符串
     * @return T 需要的类型
     */
    fun parse(data: String): T

    /**
     * 将原始数据解析成对应的字符串
     *
     * @param data Any  原始数据
     * @return String 对应的字符串
     */
    fun format(data: Any): String
}