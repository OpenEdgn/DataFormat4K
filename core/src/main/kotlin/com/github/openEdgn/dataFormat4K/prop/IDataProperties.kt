package com.github.openEdgn.dataFormat4K.prop

import java.io.Reader
import java.io.Serializable
import java.io.Writer

interface IDataProperties : Serializable {
    fun importData(properties: Reader): Long

    fun exportData(properties: Writer): Long

    fun remove(key: String): Boolean

    fun removeAll(): Boolean

    fun removeAll(key: String): Boolean

    operator fun <T : Any> get(key: String): T?

    operator fun set(key: String, value: Any)

    override fun toString(): String

    override fun hashCode(): Int

    override fun equals(other: Any?): Boolean

    fun replace(key: String, value: Any): Boolean

    @Throws(ClassCastException::class)
    fun <T : Any> getValue(key: String): T?

    fun <T : Any> getValueOrDefault(key: String, defaultValue: T): T

    fun putByte(key: String, value: Byte)

    fun putInt(key: String, value: Int)

    fun putLong(key: String, value: Long)

    fun putShort(key: String, value: Short)

    fun putDouble(key: String, value: Double)

    fun putBoolean(key: String, value: Boolean)

    fun putChar(key: String, value: Char)

    fun putString(key: String, value: String)

    fun <T : Serializable> putObject(key: String, value: T)


}