package com.github.open_edgn.data.format

import sun.misc.Unsafe
import kotlin.reflect.KClass

/**
 * 关于对象的工具方法
 */
object ObjectUtils {
    /**
     * 判断对象是否为 NULL
     * @param data T? 对象
     * @return Boolean 是否为 NULL
     */
    fun isNull(data: Any?) = data == null


    /**
     *
     * 根据 KClass 创建对象
     *
     * @param kClass KClass<T>
     * @param unsafeMode Boolean 是否使用不安全的创建方法
     * @return T 创建的实例
     */
    inline fun <reified T> createNewObject(kClass: KClass<*>, unsafeMode: Boolean): T {
        try {
            if (kClass.objectInstance != null) {
                // 判断是否为 Object 类
                return kClass.objectInstance as T
            }
            // 其余的直接通过空构造方法创建
            val declaredConstructor = kClass.java.getDeclaredConstructor()
            declaredConstructor.isAccessible = true
            return declaredConstructor.newInstance() as T
        } catch (e: Exception) {
            if (unsafeMode) {
                val field = Unsafe::class.java.getDeclaredField("theUnsafe")
                field.isAccessible = true
                val unsafe = field.get(null) as Unsafe
                return unsafe.allocateInstance(kClass.javaObjectType) as T
            } else {
                throw e
            }
        }
    }
}
