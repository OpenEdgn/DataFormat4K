package com.github.open_edgn.data.format.utils

interface IObjectFormat <T:Any>{
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
    fun parse(data: String):T
}