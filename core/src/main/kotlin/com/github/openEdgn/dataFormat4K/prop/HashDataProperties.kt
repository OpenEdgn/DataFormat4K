package com.github.openEdgn.dataFormat4K.prop

import java.io.Reader
import java.io.Writer


class HashDataProperties : BaseDataProperties() {
    private val hashMap = HashMap<String,Any>();
    override fun importData(properties: Reader, cover: Boolean): Long {
        TODO("Not yet implemented")
    }

    override fun exportData(writer: Writer): Long {
        TODO("Not yet implemented")
    }

    override fun remove(key: String): Long {
        TODO("Not yet implemented")
    }

    override fun removeAll(): Long {
        TODO("Not yet implemented")
    }

    override fun <T : Any> get(key: String): T? {
        TODO("Not yet implemented")
    }

    override fun set(key: String, value: Any) {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        TODO("Not yet implemented")
    }

    override fun hashCode(): Int {
        return hashMap.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsKey(key: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun replace(key: String, value: Any): Boolean {
        TODO("Not yet implemented")
    }

    override fun <T : Any> getValue(key: String): T? {
        TODO("Not yet implemented")
    }

    override fun <T : Any> getValueOrDefault(key: String, defaultValue: T): T {
        TODO("Not yet implemented")
    }

}