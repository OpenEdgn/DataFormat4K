package com.github.open_edgn.data.format

import java.io.Serializable

interface IDataReader : Serializable {
    /**
     * 根据键值获取 Byte 类型的数据
     *
     * 注意，如果键值不存在则返回 NULL
     *
     * @param key String 键值
     * @return Byte 类型的数据或者 NULL
     */
    fun getByte(key: String): Byte?

    /**
     * 根据键值获取 Int 类型的数据
     *
     * 注意，如果键值不存在则返回 NULL
     *
     * @param key String 键值
     * @return Int 类型的数据或者 NULL
     */
    fun getInt(key: String): Int?

    /**
     * 根据键值获取 Long 类型的数据
     *
     * 注意，如果键值不存在则返回 NULL
     *
     * @param key String 键值
     * @return Long 类型的数据或者 NULL
     */
    fun getLong(key: String): Long?

    /**
     * 根据键值获取 Float 类型的数据
     *
     * 注意，如果键值不存在则返回 NULL
     *
     * @param key String 键值
     * @return Float 类型的数据或者 NULL
     */
    fun getFloat(key: String): Float?

    /**
     * 根据键值获取 Short 类型的数据
     *
     * 注意，如果键值不存在则返回 NULL
     *
     * @param key String 键值
     * @return Short 类型的数据或者 NULL
     */
    fun getShort(key: String): Short?

    /**
     * 根据键值获取 Double 类型的数据
     *
     * 注意，如果键值不存在则返回 NULL
     *
     * @param key String 键值
     * @return Double 类型的数据或者 NULL
     */
    fun getDouble(key: String): Double?

    /**
     * 根据键值获取 Char 类型的数据
     *
     * 注意，如果键值不存在则返回 NULL
     *
     * @param key String 键值
     * @return Char 类型的数据或者 NULL
     */
    fun getChar(key: String): Char?

    /**
     * 根据键值获取 String 类型的数据
     *
     * 注意，如果键值不存在则返回 NULL
     *
     * @param key String 键值
     * @return String 类型的数据或者 NULL
     */
    fun getString(key: String): String?

    /**
     * 根据键值获取 Boolean 类型的数据
     *
     * 注意，如果键值不存在则返回 NULL
     *
     * @param key String 键值
     * @return Boolean 类型的数据或者 NULL
     */
    fun getBoolean(key: String): Boolean?


    /**
     * 根据键值获取 Byte 类型的数据
     *
     *  通过设置 defaultValue ，可以在类型不匹配或者键值不存在的情况
     *  下填充 defaultValue 的数值，如果键值存在且类型匹配，那么将返回
     *  键值下对应的数值。
     *
     * @param key String 键值
     * @param defaultValue Byte 默认数值
     * @return Byte 通过键值获取到的数值或者 defaultValue 的数值
     */
    fun getByteOrDefault(key: String, defaultValue: Byte): Byte

    /**
     * 根据键值获取 Int 类型的数据
     *
     *  通过设置 defaultValue ，可以在类型不匹配或者键值不存在的情况
     *  下填充 defaultValue 的数值，如果键值存在且类型匹配，那么将返回
     *  键值下对应的数值。
     *
     * @param key String 键值
     * @param defaultValue Int 默认数值
     * @return Int 通过键值获取到的数值或者 defaultValue 的数值
     */
    fun getIntOrDefault(key: String, defaultValue: Int): Int

    /**
     * 根据键值获取 Float 类型的数据
     *
     *  通过设置 defaultValue ，可以在类型不匹配或者键值不存在的情况
     *  下填充 defaultValue 的数值，如果键值存在且类型匹配，那么将返回
     *  键值下对应的数值。
     *
     * @param key String 键值
     * @param defaultValue Float 默认数值
     * @return Float 通过键值获取到的数值或者 defaultValue 的数值
     */
    fun getFloatOrDefault(key: String, defaultValue: Float): Float

    /**
     * 根据键值获取 Short 类型的数据
     *
     *  通过设置 defaultValue ，可以在类型不匹配或者键值不存在的情况
     *  下填充 defaultValue 的数值，如果键值存在且类型匹配，那么将返回
     *  键值下对应的数值。
     *
     * @param key String 键值
     * @param defaultValue Short 默认数值
     * @return Short 通过键值获取到的数值或者 defaultValue 的数值
     */
    fun getShortOrDefault(key: String, defaultValue: Short): Short

    /**
     * 根据键值获取 Double 类型的数据
     *
     *  通过设置 defaultValue ，可以在类型不匹配或者键值不存在的情况
     *  下填充 defaultValue 的数值，如果键值存在且类型匹配，那么将返回
     *  键值下对应的数值。
     *
     * @param key String 键值
     * @param defaultValue Double 默认数值
     * @return Double 通过键值获取到的数值或者 defaultValue 的数值
     */
    fun getDoubleOrDefault(key: String, defaultValue: Double): Double

    /**
     * 根据键值获取 Long 类型的数据
     *
     *  通过设置 defaultValue ，可以在类型不匹配或者键值不存在的情况
     *  下填充 defaultValue 的数值，如果键值存在且类型匹配，那么将返回
     *  键值下对应的数值。
     *
     * @param key String 键值
     * @param defaultValue Long 默认数值
     * @return Long 通过键值获取到的数值或者 defaultValue 的数值
     */
    fun getLongOrDefault(key: String, defaultValue: Long): Long

    /**
     * 根据键值获取 Char 类型的数据
     *
     *  通过设置 defaultValue ，可以在类型不匹配或者键值不存在的情况
     *  下填充 defaultValue 的数值，如果键值存在且类型匹配，那么将返回
     *  键值下对应的数值。
     *
     * @param key String 键值
     * @param defaultValue Char 默认数值
     * @return Char 通过键值获取到的数值或者 defaultValue 的数值
     */
    fun getCharOrDefault(key: String, defaultValue: Char): Char

    /**
     * 根据键值获取 String 类型的数据
     *
     *  通过设置 defaultValue ，可以在类型不匹配或者键值不存在的情况
     *  下填充 defaultValue 的数值，如果键值存在且类型匹配，那么将返回
     *  键值下对应的数值。
     *
     * @param key String 键值
     * @param defaultValue String 默认数值
     * @return String 通过键值获取到的数值或者 defaultValue 的数值
     */
    fun getStringOrDefault(key: String, defaultValue: String): String

    /**
     * 根据键值获取 Boolean 类型的数据
     *
     *  通过设置 defaultValue ，可以在类型不匹配或者键值不存在的情况
     *  下填充 defaultValue 的数值，如果键值存在且类型匹配，那么将返回
     *  键值下对应的数值。
     *
     * @param key String 键值
     * @param defaultValue Boolean 默认数值
     * @return Boolean 通过键值获取到的数值或者 defaultValue 的数值
     */
    fun getBooleanOrDefault(key: String, defaultValue: Boolean): Boolean


    /**
     * 数据的数目
     */
    val length: Int
}