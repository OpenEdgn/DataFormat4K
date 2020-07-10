package com.github.open_edgn.data.format.utils

/**
 * 关于对象的工具方法
 */
object ObjectUtils {
    /**
     * 判断对象是否为 NULL
     * @param data T? 对象
     * @return Boolean 是否为 NULL
     */
    inline fun <reified T> isNull(data: T?) = data == null

    
}
