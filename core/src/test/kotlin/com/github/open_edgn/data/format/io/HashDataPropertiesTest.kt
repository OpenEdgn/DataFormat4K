package com.github.open_edgn.data.format.io

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class HashDataPropertiesTest {
    private fun getEmptyHashDataProperties(): HashDataProperties {
        return HashDataProperties()
    }

    @Test
    fun putByte() {
        val properties = getEmptyHashDataProperties()
        properties.putByte("test.byte", 1)
        assertEquals(properties.getByte("test.byte"), 1)
        assertNotEquals(properties.getByte("test.byte"), 2)
    }

    @Test
    fun putInt() {
        val properties = getEmptyHashDataProperties()
        properties.putInt("test.int", 2)
        assertEquals(properties.getInt("test.int"), 2)
        assertNotEquals(properties.getInt("test.int"), 22)
    }

    @Test
    fun putLong() {
        val properties = getEmptyHashDataProperties()
        properties.putLong("test.long", 2L)
        assertEquals(properties.getLong("test.long"), 2L)
        assertNotEquals(properties.getLong("test.long"), 32L)
    }


    @Test
    fun putFloat() {
        val properties = getEmptyHashDataProperties()
        properties.putFloat("test.float", 3.2f)
        assertEquals(properties.getFloat("test.float"), 3.2f)
        assertNotEquals(properties.getFloat("test.float"), 3.4f)
    }

    @Test
    fun putShort() {
        val properties = getEmptyHashDataProperties()
        properties.putShort("test.short", 12)
        assertEquals(properties.getShort("test.short"), 12)
        assertNotEquals(properties.getShort("test.short"), 52)
    }

    @Test
    fun putDouble() {
        val properties = getEmptyHashDataProperties()
        properties.putDouble("test.double", 12.12)
        assertEquals(properties.getDouble("test.double"), 12.12)
        assertNotEquals(properties.getDouble("test.double"), 12.122)
    }

    @Test
    fun putBoolean() {
        val properties = getEmptyHashDataProperties()
        properties.putBoolean("test.boolean", true)
        assertEquals(properties.getBoolean("test.boolean"), true)
        assertNotEquals(properties.getBoolean("test.boolean"), false)
    }

    @Test
    fun putChar() {
        val properties = getEmptyHashDataProperties()
        properties.putChar("test.char", 'd')
        assertEquals(properties.getChar("test.char"), 'd')
        assertNotEquals(properties.getChar("test.char"), 'c')

    }

    @Test
    fun putString() {
        val properties = getEmptyHashDataProperties()
        properties.putFloat("test.short", 12.3f)
        properties.putString("test.string", "format data is %{test.short}.")
        assertEquals(properties.getString("test.string"), "format data is 12.3.")
    }


    @Test
    fun getByteOrDefault() {
        val prop = getEmptyHashDataProperties()
        prop.putByte("test.byte", 2)
        assertEquals(prop.getByteOrDefault("test.byte", 3), 2)
        assertEquals(prop.getByteOrDefault("test.byte.not", 3), 3)
    }

    @Test
    fun getIntOrDefault() {
        val prop = getEmptyHashDataProperties()
        prop.putInt("test.int", 2)
        assertEquals(prop.getIntOrDefault("test.int", 3), 2)
        assertEquals(prop.getIntOrDefault("test.int.not", 3), 3)
    }

    @Test
    fun getFloatOrDefault() {
        val prop = getEmptyHashDataProperties()
        prop.putFloat("test.float", 2f)
        assertEquals(prop.getFloatOrDefault("test.float", 3f), 2f)
        assertEquals(prop.getFloatOrDefault("test.float.not", 3f), 3f)
    }

    @Test
    fun getShortOrDefault() {
        val prop = getEmptyHashDataProperties()
        prop.putShort("test.short", 2)
        assertEquals(prop.getShortOrDefault("test.short", 3), 2)
        assertEquals(prop.getShortOrDefault("test.short.not", 3), 3)
    }

    @Test
    fun getDoubleOrDefault() {
        val prop = getEmptyHashDataProperties()
        prop.putDouble("test.double", 2.12)
        assertEquals(prop.getDoubleOrDefault("test.double", 3.21), 2.12)
        assertEquals(prop.getDoubleOrDefault("test.double.not", 2.14), 2.14)
    }


    @Test
    fun getCharOrDefault() {
        val prop = getEmptyHashDataProperties()
        prop.putChar("test.char", 'a')
        assertEquals(prop.getCharOrDefault("test.char", 'd'), 'a')
        assertEquals(prop.getCharOrDefault("test.char.not", 'd'), 'd')
    }

    @Test
    fun getStringOrDefault() {
        val prop = getEmptyHashDataProperties()
        prop.putInt("test.int", 2)
        prop.putString("test.string", "put extra.")
        assertEquals(prop.getStringOrDefault("test.string", "default value"), "put extra.")
        assertEquals(prop.getStringOrDefault("test.string2", "default value is:%{test.int}."), "default value is:2.")
    }

    @Test
    fun getBooleanOrDefault() {
        val prop = getEmptyHashDataProperties()
        prop.putBoolean("test.bool", true)
        assertEquals(prop.getBooleanOrDefault("test.bool", false), true)
        assertEquals(prop.getBooleanOrDefault("test.bool.not", false), false)
    }

    @Test
    fun getLongOrDefault() {
        val prop = getEmptyHashDataProperties()
        prop.putLong("test.long", 122312334124L)
        assertEquals(prop.getLongOrDefault("test.long", 312121L),  122312334124L)
        assertEquals(prop.getLongOrDefault("test.long.not", 312121L), 312121L)
    }


    @Test
    fun remove() {
        val prop = getEmptyHashDataProperties()
        prop.putLong("long",121L)
        prop.putLong("long2",1221L)
        prop.putLong("long3",12221L)
        assertEquals(prop.length,3)
        prop.remove("long3")
        assertEquals(prop.length,2)
    }

    @Test
    fun removeAll() {
        val prop = getEmptyHashDataProperties()
        prop.putLong("long",121L)
        prop.putLong("long2",1221L)
        prop.putLong("long3",12221L)
        assertEquals(prop.length,3)
        prop.removeAll()
        assertEquals(prop.length,0)
    }


    @Test
    fun containsKey() {
        val prop = getEmptyHashDataProperties()
        prop.putLong("long",121L)
        prop.putLong("long2",1221L)
        prop.putLong("long3",12221L)
        assertTrue(prop.containsKey("long3"))
        assertFalse(prop.containsKey("long4"))
    }

    @Test
    fun replace() {
        val prop = getEmptyHashDataProperties()
        prop.putLong("long",121L)
        prop.putLong("long2",1221L)
        prop.putLong("long3",12221L)
        assertEquals(prop.getLong("long3"),12221L)
        assertTrue(prop.replace("long3",2343L))
        assertFalse(prop.replace("long4",2121L))
        assertEquals(prop.getLong("long3"),2343L)
    }



}