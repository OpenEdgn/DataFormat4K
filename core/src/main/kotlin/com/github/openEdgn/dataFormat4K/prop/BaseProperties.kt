package com.github.openEdgn.dataFormat4K.prop

import java.io.*
import java.util.*

/**
 * 数据序列化工具
 *
 * 自定义的数据加载、保存
 *
 */
abstract class BaseProperties : IDataProperties {


    override fun putByte(key: String, value: Byte) {
        TODO("Not yet implemented")
    }

    override fun putInt(key: String, value: Int) {
        TODO("Not yet implemented")
    }

    override fun putLong(key: String, value: Long) {
        TODO("Not yet implemented")
    }

    override fun putShort(key: String, value: Short) {
        TODO("Not yet implemented")
    }

    override fun putDouble(key: String, value: Double) {
        TODO("Not yet implemented")
    }

    override fun putBoolean(key: String, value: Boolean) {
        TODO("Not yet implemented")
    }

    override fun putChar(key: String, value: Char) {
        TODO("Not yet implemented")
    }

    override fun putString(key: String, value: String) {
        TODO("Not yet implemented")
    }

    override fun <T : Serializable> putObject(key: String, value: T) {
        TODO("Not yet implemented")
    }

    override fun getByte(key: String): Byte? {
        TODO("Not yet implemented")
    }

    override fun getInt(key: String): Int? {
        TODO("Not yet implemented")
    }

    override fun getShort(key: String): Short? {
        TODO("Not yet implemented")
    }

    override fun getDouble(key: String): Double? {
        TODO("Not yet implemented")
    }

    override fun getChar(key: String): Char? {
        TODO("Not yet implemented")
    }

    override fun getString(key: String): String? {
        TODO("Not yet implemented")
    }

    override fun getBoolean(key: String): Boolean? {
        TODO("Not yet implemented")
    }

    override fun <T : Serializable> getObject(key: String): T? {
        TODO("Not yet implemented")
    }

    override fun getByteOrDefault(key: String, defaultValue: Byte): Byte {
        TODO("Not yet implemented")
    }

    override fun getIntOrDefault(key: String, defaultValue: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getShortOrDefault(key: String, defaultValue: Short): Short {
        TODO("Not yet implemented")
    }

    override fun getDoubleOrDefault(key: String, defaultValue: Double): Double {
        TODO("Not yet implemented")
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
}