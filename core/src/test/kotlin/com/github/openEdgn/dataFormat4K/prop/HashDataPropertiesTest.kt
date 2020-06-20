package com.github.openEdgn.dataFormat4K.prop

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory


class HashDataPropertiesTest{
    private val logger = LoggerFactory.getLogger(javaClass)

    @Test
    fun importData() {
    }

    @Test
    fun exportData() {
    }

    @Test
    fun remove() {
    }

    @Test
    fun removeAll() {
    }

    @Test
    fun get() {
    }

    @Test
    fun set() {
    }

    @Test
    fun testToString() {
    }

    @Test
    fun testHashCode() {
    }

    @Test
    fun testEquals() {
    }

    @Test
    fun containsKey() {
    }

    @Test
    fun replace() {
    }

    @Test
    fun getValue() {
    }

    @Test
    fun getValueOrDefault() {

    }

    @Test
    fun getString() {
    }

    @Test
    fun getStringOrDefault() {
        val prop = createEmpty()
        prop.putString("key.empty","这是空白的数据，%{user.dir}。")
        prop.putString("key.empty2","这是空白的数据，%{key.empty}。")
        println(prop.getString("key.empty"))
        println(prop.getString("key.empty2"))
    }

    private fun createEmpty(): HashDataProperties {
        return HashDataProperties()
    }

}