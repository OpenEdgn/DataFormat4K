package com.github.openEdgn.dataFormat4K.prop

import java.io.Reader
import java.io.Serializable
import java.io.Writer

interface IDataProperties : Serializable{
    /**
     * 从流中导入数据
     * @param properties Reader 字符流
     * @param coverData 如果键冲突是否覆盖
     * @return Long 导入的数据数目
     */
    fun importData(properties: Reader, coverData: Boolean): Long

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
     *   根据键值来设置数据
     *
     *   会覆盖原有的键值相同的数据，不管以前是什么类型的数据
     *
     * @param key String 键值
     * @param value Any 数据
     * @return 如果数据不符合规范（无法添加）则返回 false
     */
    operator fun set(key: String, value: Any):Boolean

    override fun toString(): String

    override fun hashCode(): Int

    override fun equals(other: Any?): Boolean

    /**
     * 判断是否存在此键值的数据
     *
     * @param key String 键值
     * @return Boolean 是否存在
     */
    fun containsKey(key: String):Boolean

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
     *
     * 设置 Byte 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Byte 类型的数据
     */
    fun putByte(key: String, value: Byte)

    /**
     *
     * 设置 Int 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Int 类型的数据
     */
    fun putInt(key: String, value: Int)
    /**
     *
     * 设置 Float 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Float 类型的数据
     */
    fun putFloat(key: String, value: Float)
    /**
     *
     * 设置 Long 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Long 类型的数据
     */
    fun putLong(key: String, value: Long)

    /**
     *
     * 设置 Short 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Short 类型的数据
     */
    fun putShort(key: String, value: Short)

    /**
     *
     * 设置 Double 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Double 类型的数据
     */
    fun putDouble(key: String, value: Double)

    /**
     *
     * 设置 Boolean 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Boolean 类型的数据
     */
    fun putBoolean(key: String, value: Boolean)

    /**
     *
     * 设置 Char 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Char 类型的数据
     */
    fun putChar(key: String, value: Char)

    /**
     *
     * 设置 String 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value String 类型的数据
     */
    fun putString(key: String, value: String)

    /**
     *
     * 设置继承于Serializable类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value 继承于Serializable的数据
     */
    fun <T : Serializable> putObject(key: String, value: T)

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
     * 根据键值获取 继承于Serializable类型的数据
     *
     * 注意，如果键值不存在则返回 NULL
     *
     * @param key String 键值
     * @return 继承于Serializable类型的数据或者 NULL
     */
    fun <T : Serializable> getObject(key: String): T?


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
     * 根据键值获取继承于Serializable类型的数据
     *
     *  通过设置 defaultValue ，可以在类型不匹配或者键值不存在的情况
     *  下填充 defaultValue 的数值，如果键值存在且类型匹配，那么将返回
     *  键值下对应的数值。
     *
     * @param key String 键值
     * @param defaultValue 默认数值
     * @return 继承于Serializable 通过键值获取到的数值或者 defaultValue 的数值
     */
    fun <T : Serializable> getObjectOrDefault(key: String, defaultValue: T): T


}