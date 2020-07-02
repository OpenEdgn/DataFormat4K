package com.github.open_edgn.data.format.io

import org.slf4j.Logger
import org.slf4j.LoggerFactory


abstract class BaseDataProperties : IDataProperties {
    protected val logger: Logger = LoggerFactory.getLogger(javaClass)
    override fun getByteOrDefault(key: String, defaultValue: Byte): Byte {
        return getByte(key) ?: defaultValue
    }

    override fun getIntOrDefault(key: String, defaultValue: Int): Int {
        return getInt(key) ?: defaultValue
    }

    override fun getFloatOrDefault(key: String, defaultValue: Float): Float {
        return getFloat(key) ?: defaultValue
    }

    override fun getShortOrDefault(key: String, defaultValue: Short): Short {
        return getShort(key) ?: defaultValue
    }

    override fun getDoubleOrDefault(key: String, defaultValue: Double): Double {
        return getDouble(key) ?: defaultValue
    }

    override fun getLongOrDefault(key: String, defaultValue: Long): Long {
        return getLong(key) ?: defaultValue
    }

    override fun getCharOrDefault(key: String, defaultValue: Char): Char {
        return getChar(key) ?: defaultValue
    }

    override fun getStringOrDefault(key: String, defaultValue: String): String {
        return getString(key) ?: defaultValue
    }

    override fun getBooleanOrDefault(key: String, defaultValue: Boolean): Boolean {
        return getBoolean(key) ?: defaultValue
    }

    override fun putByte(key: String, value: Byte): IDataProperties {
        set(key,value)
        return this
    }

    override fun putInt(key: String, value: Int): IDataProperties {
        set(key,value)
        return this
    }

    override fun putFloat(key: String, value: Float): IDataProperties {
        set(key,value)
        return this
    }

    override fun putLong(key: String, value: Long): IDataProperties {
        set(key,value)
        return this
    }

    override fun putShort(key: String, value: Short): IDataProperties {
        set(key,value)
        return this
    }

    override fun putDouble(key: String, value: Double): IDataProperties {
        set(key,value)
        return this
    }

    override fun putBoolean(key: String, value: Boolean): IDataProperties {
        set(key,value)
        return this
    }

    override fun putChar(key: String, value: Char): IDataProperties {
        set(key,value)
        return this
    }

    override fun putString(key: String, value: String): IDataProperties {
        set(key,value)
        return this
    }
}