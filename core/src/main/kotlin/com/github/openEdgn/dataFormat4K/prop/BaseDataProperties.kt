package com.github.openEdgn.dataFormat4K.prop

import com.github.openEdgn.dataFormat4K.prop.enums.DataType
import org.slf4j.LoggerFactory


abstract class BaseDataProperties : IDataProperties {
    protected val logger = LoggerFactory.getLogger(javaClass)!!


    abstract fun setValue(key: String, value: Any, dataType: DataType): Boolean
    protected abstract fun <T> getValue(key: String, dataType: DataType): T?

    override fun <T> set(key: String, value: T): Boolean {
        val type = DataType.format(value as Any)
        if (type == DataType.UNKNOWN) {
            logger.debug("添加了新字段{},但数据类型{}并非可序列化的类型，不允许添加！", key, value.javaClass.simpleName)
            return false
        }
        return setValue(key, value, type)
    }

    override fun putByte(key: String, value: Byte): IDataProperties {
        setValue(key, value, DataType.BYTE)
        return this
    }

    override fun putInt(key: String, value: Int): IDataProperties {
        setValue(key, value, DataType.INTEGER)
        return this
    }

    override fun putFloat(key: String, value: Float): IDataProperties {
        setValue(key, value, DataType.FLOAT)
        return this
    }

    override fun putLong(key: String, value: Long): IDataProperties {
        setValue(key, value, DataType.LONG)
        return this
    }

    override fun putShort(key: String, value: Short): IDataProperties {
        setValue(key, value, DataType.SHORT)
        return this
    }

    override fun putDouble(key: String, value: Double): IDataProperties {
        setValue(key, value, DataType.DOUBLE)
        return this
    }

    override fun putBoolean(key: String, value: Boolean): IDataProperties {
        setValue(key, value, DataType.BOOLEAN)
        return this
    }

    override fun putChar(key: String, value: Char): IDataProperties {
        setValue(key, value, DataType.CHAR)
        return this
    }

    override fun putString(key: String, value: String): IDataProperties {
        setValue(key, value, DataType.STRING)
        return this
    }


    override fun getByte(key: String): Byte? {
        return getValue<Byte>(key, DataType.BYTE)
    }

    override fun getInt(key: String): Int? {
        return getValue<Int>(key, DataType.INTEGER)
    }

    override fun getFloat(key: String): Float? {
        return getValue<Float>(key, DataType.FLOAT)
    }

    override fun getShort(key: String): Short? {
        return getValue<Short>(key, DataType.SHORT)
    }

    override fun getDouble(key: String): Double? {
        return getValue<Double>(key, DataType.DOUBLE)
    }

    override fun getChar(key: String): Char? {
        return getValue<Char>(key, DataType.CHAR)
    }

    override fun getString(key: String): String? {
        return getValue<String>(key, DataType.STRING)
    }

    override fun getBoolean(key: String): Boolean? {
        return getValue<Boolean>(key, DataType.BOOLEAN)
    }

    override fun getLong(key: String): Long? {
        return getValue<Long>(key, DataType.LONG)

    }

    override fun getByteOrDefault(key: String, defaultValue: Byte): Byte {
        return resultValueOrNull(getByte(key), defaultValue)
    }

    override fun getIntOrDefault(key: String, defaultValue: Int): Int {
        return resultValueOrNull(getInt(key), defaultValue)
    }

    override fun getFloatOrDefault(key: String, defaultValue: Float): Float {
        return resultValueOrNull(getFloat(key), defaultValue)
    }

    override fun getShortOrDefault(key: String, defaultValue: Short): Short {
        return resultValueOrNull(getShort(key), defaultValue)
    }

    override fun getDoubleOrDefault(key: String, defaultValue: Double): Double {
        return resultValueOrNull(getDouble(key), defaultValue)
    }

    override fun getCharOrDefault(key: String, defaultValue: Char): Char {
        return resultValueOrNull(getChar(key), defaultValue)
    }

    override fun getStringOrDefault(key: String, defaultValue: String): String {
        return resultValueOrNull(getString(key), defaultValue)
    }

    override fun getBooleanOrDefault(key: String, defaultValue: Boolean): Boolean {
        return resultValueOrNull(getBoolean(key), defaultValue)
    }

    override fun getLongOrDefault(key: String, defaultValue: Long): Long {
        return resultValueOrNull(getLong(key), defaultValue)
    }

    private fun <T> resultValueOrNull(value: T?, defaultValue: T): T {
        return value ?: defaultValue
    }
}