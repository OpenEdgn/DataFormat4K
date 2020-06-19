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


    fun getByte(key: String): Byte?

    fun getInt(key: String): Int?

    fun getShort(key: String): Short?

    fun getDouble(key: String): Short?

    fun getChar(key: String): Char?

    fun getString(key: String): String?

    fun getBoolean(key: String): Boolean?

    fun <T : Serializable> getObject(key: String): T?


    fun getByteOrDefault(key: String,defaultValue:Byte): Byte

    fun getIntOrDefault(key: String,defaultValue:Int): Int

    fun getShortOrDefault(key: String,defaultValue:Short): Short

    fun getDoubleOrDefault(key: String,defaultValue:Double): Double

    fun getCharOrDefault(key: String,defaultValue:Char): Char

    fun getStringOrDefault(key: String,defaultValue:String): String

    fun getBooleanOrDefault(key: String,defaultValue:Boolean): Boolean

    fun <T : Serializable> getObjectOrDefault(key: String,defaultValue:T): T



}