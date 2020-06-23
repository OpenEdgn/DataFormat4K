package com.github.openEdgn.dataFormat4K.prop

import org.slf4j.LoggerFactory
import java.io.*


abstract class BaseDataProperties : IDataProperties {
    protected val logger = LoggerFactory.getLogger(javaClass)!!

    enum class DataType(val clazz: Class<*>) {
        BYTE(Byte::class.java),
        FLOAT(Float::class.java),
        INTEGER(Int::class.java),
        LONG(Long::class.java),
        SHORT(SHORT::class.java),
        DOUBLE(DOUBLE::class.java),
        BOOLEAN(Boolean::class.java),
        CHAR(CHAR::class.java),
        STRING(String::class.java),
        SERIALIZABLE(Serializable::class.java),
        UNKNOWN(Any::class.java);

        companion object {

            fun format(any: Any): DataType {
                values().forEach {
                    if (it != UNKNOWN) {
                        if (any.javaClass.isAssignableFrom(it.clazz)) {
                            return it
                        }
                    }
                }
                return UNKNOWN
            }
        }
    }

    abstract fun setValue(key: String, value: Any, dataType: DataType): Boolean
    abstract fun <T> getValue(key: String, dataType: DataType): T?

    override fun set(key: String, value: Any): Boolean {
        val type = DataType.format(value)
        if (type == DataType.UNKNOWN) {
            logger.debug("添加了新字段{},但数据类型{}并非可序列化的类型，不允许添加！", key, value.javaClass.simpleName)
            return false
        }
        return setValue(key, value, type)
    }

    override fun putByte(key: String, value: Byte) {
        setValue(key, value, DataType.BYTE)
    }

    override fun putInt(key: String, value: Int) {
        setValue(key, value, DataType.INTEGER)
    }

    override fun putFloat(key: String, value: Float) {
        setValue(key, value, DataType.FLOAT)
    }

    override fun putLong(key: String, value: Long) {
        setValue(key, value, DataType.LONG)
    }

    override fun putShort(key: String, value: Short) {
        setValue(key, value, DataType.SHORT)
    }

    override fun putDouble(key: String, value: Double) {
        setValue(key, value, DataType.DOUBLE)
    }

    override fun putBoolean(key: String, value: Boolean) {
        setValue(key, value, DataType.BOOLEAN)
    }

    override fun putChar(key: String, value: Char) {
        setValue(key, value, DataType.CHAR)
    }

    override fun putString(key: String, value: String) {
        setValue(key, value, DataType.STRING)
    }

    override fun <T : Serializable> putObject(key: String, value: T) {
        setValue(key, value, DataType.SERIALIZABLE)
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

    override fun <T : Serializable> getObject(key: String): T? {
        return getValue<T>(key, DataType.SERIALIZABLE)
    }

    override fun getByteOrDefault(key: String, defaultValue: Byte): Byte {
        return resultValueOrNull(getByte(key),defaultValue)
    }

    override fun getIntOrDefault(key: String, defaultValue: Int): Int {
        return resultValueOrNull(getInt(key),defaultValue)
    }

    override fun getFloatOrDefault(key: String, defaultValue: Float): Float {
        return resultValueOrNull(getFloat(key),defaultValue)
    }

    override fun getShortOrDefault(key: String, defaultValue: Short): Short {
        return resultValueOrNull(getShort(key),defaultValue)
    }

    override fun getDoubleOrDefault(key: String, defaultValue: Double): Double {
        return resultValueOrNull(getDouble(key),defaultValue)
    }

    override fun getCharOrDefault(key: String, defaultValue: Char): Char {
        TODO("Not yet implemented")
    }

    override fun getStringOrDefault(key: String, defaultValue: String): String {
        TODO("Not yet implemented")
    }

    override fun getBooleanOrDefault(key: String, defaultValue: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun <T : Serializable> getObjectOrDefault(key: String, defaultValue: T): T {
        TODO("Not yet implemented")
    }

    private fun <T> resultValueOrNull(value: T?,defaultValue: T): T {
        return value ?: defaultValue
    }
}