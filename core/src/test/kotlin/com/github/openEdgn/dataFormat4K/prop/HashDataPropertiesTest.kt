package com.github.openEdgn.dataFormat4K.prop

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class HashDataPropertiesTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    private fun getEmptyHashDataProperties(ignoreCase: Boolean = true): HashDataProperties {
        return HashDataProperties()
    }
    @Test
    fun putByte() {
        val properties = getEmptyHashDataProperties()
        properties.putByte("test.byte",1)
        assertEquals(properties.getByte("test.byte"),1)
    }

    @Test
    fun putInt() {
        val properties = getEmptyHashDataProperties()
        properties.putInt("test.int",2)
        assertEquals(properties.getInt("test.int"),2)
    }

    @Test
    fun putLong() {
        val properties = getEmptyHashDataProperties()
        properties.putLong("test.long",2L)
        assertEquals(properties.getLong("test.long"),2L)
    }


    @Test
    fun putFloat() {
        val properties = getEmptyHashDataProperties()
        properties.putFloat("test.float",3.2f)
        assertEquals(properties.getFloat("test.float"),3.2f)
    }

    @Test
    fun putShort() {
        val properties = getEmptyHashDataProperties()
        properties.putShort("test.short",12)
        assertEquals(properties.getShort("test.short"),12)
    }

    @Test
    fun putDouble() {
    }

    @Test
    fun putBoolean() {
    }

    @Test
    fun putChar() {
    }

    @Test
    fun putString() {
        val properties = getEmptyHashDataProperties()
        properties.putFloat("test.short",12.3f)
        properties.putString("test.string","format data is %{test.short}.")
        assertEquals(properties.getString("test.string"),"format data is 12.3.")
    }

    @Test
    fun getByte() {
    }

    @Test
    fun getInt() {
    }

    @Test
    fun getFloat() {
    }

    @Test
    fun getShort() {
    }

    @Test
    fun getDouble() {
    }

    @Test
    fun getChar() {
    }

    @Test
    fun getString() {
    }

    @Test
    fun getBoolean() {
    }

    @Test
    fun getLong() {
    }

    @Test
    fun getByteOrDefault() {
    }

    @Test
    fun getIntOrDefault() {
    }

    @Test
    fun getFloatOrDefault() {
    }

    @Test
    fun getShortOrDefault() {
    }

    @Test
    fun getDoubleOrDefault() {
    }

    @Test
    fun getCharOrDefault() {
    }

    @Test
    fun getStringOrDefault() {
    }

    @Test
    fun getBooleanOrDefault() {
    }

    @Test
    fun getLongOrDefault() {
    }

    @Test
    fun setValue() {
    }

    @Test
    fun getValue() {
    }

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
    fun testToString() {
    }

    @Test
    fun containsKey() {
    }

    @Test
    fun replace() {
    }

    @Test
    fun testGetString() {
    }

    @Test
    fun testGetStringOrDefault() {
    }
}