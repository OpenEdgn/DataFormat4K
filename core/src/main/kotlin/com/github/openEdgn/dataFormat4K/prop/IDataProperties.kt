package com.github.openEdgn.dataFormat4K.prop

import java.io.Reader
import java.io.Serializable
import java.io.Writer

interface IDataProperties : Serializable {
    /**
     * 从流中导入数据
     * @param properties Reader 字符流
     * @param cover 如果键冲突是否覆盖
     * @return Long 导入的数据数目
     */
    fun importData(properties: Reader, cover: Boolean): Long

    /**
     * 导出数据到流中
     * @param writer Writer 目标流出口
     * @return Long 导出的数据数目
     */
    fun exportData(writer: Writer): Long

    /**
     * 根据键值来删除数据
     * @param key String 键值
     * @return Long 删除的数目个数
     */
    fun remove(key: String): Long

    /**
     * 删除所有数据
     * @return Long 删除的数目个数
     */
    fun removeAll(): Long

    /**
     * 根据键值获取一条数据
     *
     * 如果数据不存在则返回 `null`
     *
     * @param key String 键值
     * @return T? 已查到的数据或者NULL
     */
    operator fun <T : Any> get(key: String): T?

    /**
     *   根据键值来设置数据
     *
     *   会覆盖原有的键值相同的数据，不管以前是什么类型的数据
     *
     * @param key String 键值
     * @param value Any 数据
     */
    operator fun set(key: String, value: Any)


    override fun toString(): String

    override fun hashCode(): Int

    override fun equals(other: Any?): Boolean

    /**
     * 将键值下的数据替换为新的数据
     *
     * 注意，仅为替换，如果键值对不存在那么将返回false
     *
     * @param key String 键
     * @param value Any 新的数据
     * @return Boolean 是否替换成功
     */
    fun replace(key: String, value: Any): Boolean

    /**
     * 根据键值获取一条数据
     *
     * 如果数据不存在则返回 `null`
     *
     * @param key String 键值
     * @return T? 已查到的数据或者NULL
     */
    fun <T : Any> getValue(key: String): T?

    /**
     * 根据Key 获取一条数据
     *
     * 如果数据不存在则返回 defaultValue 下的数据
     *
     * @param key String 键值
     * @param defaultValue 默认数据
     * @return T 已查到的数据或者默认数据
     */
    fun <T : Any> getValueOrDefault(key: String, defaultValue: T): T

    /**
     *
     * 设置 Byte 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Byte 类型的数据
     */
    fun putByte(key: String, value: Byte)

    fun putInt(key: String, value: Int)

    fun putLong(key: String, value: Long)

    fun putShort(key: String, value: Short)

    fun putDouble(key: String, value: Double)

    fun putBoolean(key: String, value: Boolean)

    fun putChar(key: String, value: Char)

    fun putString(key: String, value: String)

    fun <T : Serializable> putObject(key: String, value: T)


    fun getByte(key: String): Byte?

    fun getInt(key: String): Int?

    fun getShort(key: String): Short?

    fun getDouble(key: String): Short?

    fun getChar(key: String): Char?

    fun getString(key: String): String?

    fun getBoolean(key: String): Boolean?

    fun <T : Serializable> getObject(key: String): T?


    fun getByteOrDefault(key: String, defaultValue: Byte): Byte

    fun getIntOrDefault(key: String, defaultValue: Int): Int

    fun getShortOrDefault(key: String, defaultValue: Short): Short

    fun getDoubleOrDefault(key: String, defaultValue: Double): Double

    fun getCharOrDefault(key: String, defaultValue: Char): Char

    fun getStringOrDefault(key: String, defaultValue: String): String

    fun getBooleanOrDefault(key: String, defaultValue: Boolean): Boolean

    fun <T : Serializable> getObjectOrDefault(key: String, defaultValue: T): T


}