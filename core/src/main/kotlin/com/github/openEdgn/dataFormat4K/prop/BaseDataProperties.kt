package com.github.openEdgn.dataFormat4K.prop

import org.slf4j.LoggerFactory
import java.io.*
import java.util.*


abstract class BaseDataProperties : IDataProperties {
    protected val logger = LoggerFactory.getLogger(javaClass)

    override fun putByte(key: String, value: Byte) {
        set(key, value)
    }

    override fun putInt(key: String, value: Int) {
        set(key, value)
    }

    override fun putLong(key: String, value: Long) {
        set(key, value)
    }

    override fun putShort(key: String, value: Short) {
        set(key, value)
    }

    override fun putDouble(key: String, value: Double) {
        set(key, value)
    }

    override fun putBoolean(key: String, value: Boolean) {
        set(key, value)
    }

    override fun putChar(key: String, value: Char) {
        set(key, value)
    }

    override fun putString(key: String, value: String) {
        set(key, value)
    }

    override fun <T : Serializable> putObject(key: String, value: T) {
        set(key, value)
    }

    override fun <T : Any> getValue(key: String): T? {
        return get(key)
    }

    override fun <T : Any> getValueOrDefault(key: String, defaultValue: T): T {
        return getValue(key) ?: defaultValue
    }

    override fun getByte(key: String): Byte? = get(key)

    override fun getInt(key: String): Int? = get(key)

    override fun getShort(key: String): Short? = get(key)

    override fun getDouble(key: String): Double? = get(key)

    override fun getChar(key: String): Char? = get(key)

    override fun getString(key: String): String? = get(key)

    override fun getBoolean(key: String): Boolean? = get(key)

    override fun <T : Serializable> getObject(key: String): T? = get(key)

    override fun getByteOrDefault(key: String, defaultValue: Byte): Byte = getValueOrDefault(key, defaultValue)

    override fun getIntOrDefault(key: String, defaultValue: Int): Int = getValueOrDefault(key, defaultValue)

    override fun getShortOrDefault(key: String, defaultValue: Short): Short = getValueOrDefault(key, defaultValue)

    override fun getDoubleOrDefault(key: String, defaultValue: Double): Double = getValueOrDefault(key, defaultValue)

    override fun getCharOrDefault(key: String, defaultValue: Char): Char = getValueOrDefault(key, defaultValue)

    override fun getStringOrDefault(key: String, defaultValue: String): String = getValueOrDefault(key, defaultValue)

    override fun getBooleanOrDefault(key: String, defaultValue: Boolean): Boolean = getValueOrDefault(key, defaultValue)

    override fun <T : Serializable> getObjectOrDefault(key: String, defaultValue: T): T = getValueOrDefault(key, defaultValue)
}