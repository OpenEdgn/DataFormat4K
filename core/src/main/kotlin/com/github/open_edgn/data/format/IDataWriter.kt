package com.github.open_edgn.data.format

import java.io.Serializable

interface IDataWriter : Serializable {
    /**
     * 根据键值来删除数据
     * @param key String 键值
     * @return Int 删除的数目个数
     */

    fun remove(key: String): Int

    /**
     * 删除所有数据
     * @return Int 删除的数目个数
     */
    fun removeAll(): Int


    /**
     *   根据键值来设置数据
     *
     *   会覆盖原有的键值相同的数据，不管以前是什么类型的数据
     *
     * @param key String 键值
     * @param value Any 数据
     * @return 如果数据不符合规范（无法添加）则返回 false
     */
    operator fun <T> set(key: String, value: T): Boolean


    /**
     * 判断是否存在此键值的数据
     *
     * @param key String 键值
     * @return Boolean 是否存在
     */
    fun containsKey(key: String): Boolean

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
    fun putByte(key: String, value: Byte): IDataProperties

    /**
     *
     * 设置 Int 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Int 类型的数据
     */
    fun putInt(key: String, value: Int): IDataProperties

    /**
     *
     * 设置 Float 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Float 类型的数据
     */
    fun putFloat(key: String, value: Float): IDataProperties

    /**
     *
     * 设置 Long 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Long 类型的数据
     */
    fun putLong(key: String, value: Long): IDataProperties

    /**
     *
     * 设置 Short 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Short 类型的数据
     */
    fun putShort(key: String, value: Short): IDataProperties

    /**
     *
     * 设置 Double 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Double 类型的数据
     */
    fun putDouble(key: String, value: Double): IDataProperties

    /**
     *
     * 设置 Boolean 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Boolean 类型的数据
     */
    fun putBoolean(key: String, value: Boolean): IDataProperties

    /**
     *
     * 设置 Char 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value Char 类型的数据
     */
    fun putChar(key: String, value: Char): IDataProperties

    /**
     *
     * 设置 String 类型的数据到 key 下
     *
     *  默认会覆盖原有的键值相同的数据
     *
     * @param key String 键值
     * @param value String 类型的数据
     */
    fun putString(key: String, value: String): IDataProperties

    /**
     * 数据的数目
     */
    val length: Int

}