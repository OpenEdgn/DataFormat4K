package com.github.openEdgn.dataFormat4K.prop

import java.io.*
import java.util.*

/**
 * 数据序列化工具
 *
 * 自定义的数据加载、保存
 *
 */
abstract class BaseProperties : Serializable {
    abstract fun importData(properties: Reader):Long
    abstract fun exportData(properties: Writer):Long
    abstract fun remove(key: String): Boolean
    abstract fun removeAll(): Boolean
    abstract fun removeAll(key:String): Boolean
    abstract operator fun <T : Any> get(key: String): T?
    abstract operator fun set(key: String, value: Any)
    abstract override fun toString(): String
    abstract override fun hashCode(): Int
    abstract override fun equals(other: Any?): Boolean



    abstract fun replace(key: String, value: Any): Boolean

    /**
     * 通过 key 键 获取相应的数值，不存在则返回 NULL
     *
     * 注意，由于类型自动强制转换，如果转换失败则会抛出异常
     *
     * @param key String  key键
     * @return T? 依据返回值得到类型或者 NULL
     */
    open fun <T: Any> getValue(key: String): T? = this[key]

    /**
     * 通过 key 键 获取相应的数值，不存在则返回默认值
     *
     * @param key String key键
     * @param defaultValue T 默认返回类型
     * @return T  依据返回值得到类型
     */
    open fun <T : Any> getValue(key: String, defaultValue: T): T = getValue(key) ?: defaultValue

    /**
     * 添加 Byte 类型的数据
     *
     *  注意，添加此键值对将会覆盖同键值的数据
     *
     * @param key 键
     * @param value Byte 类型的数据
     */
    open fun putByte(key: String, value: Byte) {
        this[key] = value
    }

    /**
     * 添加 Int 类型的数据
     *
     *  注意，添加此键值对将会覆盖同键值的数据
     *
     * @param key 键
     * @param value Int 类型的数据
     */
    open fun putInt(key: String, value: Int) {
        this[key] = value

    }

    /**
     * 添加 Long 类型的数据
     *
     *  注意，添加此键值对将会覆盖同键值的数据
     *
     * @param key 键
     * @param value Long 类型的数据
     */
    open fun putLong(key: String, value: Long) {
        this[key] = value

    }

    /**
     * 添加 Short 类型的数据
     *
     *  注意，添加此键值对将会覆盖同键值的数据
     *
     * @param key 键
     * @param value Short 类型的数据
     */
    open fun putShort(key: String, value: Short) {
        this[key] = value
    }

    /**
     * 添加 Double 类型的数据
     *
     *  注意，添加此键值对将会覆盖同键值的数据
     *
     * @param key 键
     * @param value Double 类型的数据
     */
    open fun putDouble(key: String, value: Double) {
        this[key] = value
    }

    /**
     * 添加 Boolean 类型的数据
     *
     *  注意，添加此键值对将会覆盖同键值的数据
     *
     * @param key 键
     * @param value Boolean 类型的数据
     */
    open fun putBoolean(key: String, value: Boolean) {
        this[key] = value
    }

    /**
     * 添加 Char 类型的数据
     *
     *  注意，添加此键值对将会覆盖同键值的数据
     *
     * @param key 键
     * @param value Char 类型的数据
     */
    open fun putChar(key: String, value: Char) {
        this[key] = value
    }

    /**
     * 添加 String 类型的数据
     *
     *  注意，添加此键值对将会覆盖同键值的数据
     *
     * @param key 键
     * @param value String 类型的数据
     */
    open fun putString(key: String, value: String) {
        this[key] = value
    }

    /**
     *  得到 Byte 类型的数据
     *
     *  可通过设置 `defaultValue` 属性来指定默认返回值 （如果数据不存在的话）
     *
     * @param key String
     * @param defaultValue Byte
     * @return Byte
     */
    open fun getByte(key: String, defaultValue: Byte = 0): Byte {
        return getValue(key,defaultValue)
    }

    open fun getInt(key: String, defaultValue: Int = 0): Int {
        return getValue(key,defaultValue)
    }

    open fun getFloat(key: String, defaultValue: Float = 0f): Float {
        return getValue(key,defaultValue)
    }

    open fun getDouble(key: String, defaultValue: Double = 0.0): Double {
        return getValue(key,defaultValue)
    }

    open fun getLong(key: String, defaultValue: Long = 0): Long {
        return getValue(key,defaultValue)
    }

    open fun getChar(key: String, defaultValue: Char = 0.toChar()): Char {
        return getValue(key,defaultValue)
    }
    open fun getString(key: String, defaultValue: String = ""): String {
        return getValue(key,defaultValue)
    }

}