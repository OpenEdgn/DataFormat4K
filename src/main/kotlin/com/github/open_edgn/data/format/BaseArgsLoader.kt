package com.github.open_edgn.data.format

import kotlin.reflect.KClass


abstract class BaseArgsLoader {


    /**
     * 得到 args 的实例化注入的对象
     *
     * @param kClass KClass<T> 对象 Class
     * @return T 实例化的对象
     */
    abstract fun <T : Any> getArgsBean(kClass: KClass<T>): T
}