package com.github.open_edgn.data.format

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.IOException

internal class StringUtilsTest {

    @Test
    fun readText() {
        val testData = "Hello World"
        assertEquals(StringUtils.readText(testData.byteInputStream(Charsets.UTF_8)), testData)
    }

    @Test
    fun testReadText() {

        val testData = "Hello World"
        assertEquals(StringUtils.readText(testData.reader()), testData)
    }

    @Test
    fun isNullOrEmpty() {
        assertTrue(StringUtils.isNullOrEmpty(null))
        assertTrue(StringUtils.isNullOrEmpty(""))
        assertFalse(StringUtils.isNullOrEmpty("Hello World"))
    }

    @Test
    fun isNullOrBlank() {
        assertTrue(StringUtils.isNullOrBlank(null))
        assertTrue(StringUtils.isNullOrBlank(""))
        assertTrue(StringUtils.isNullOrBlank(" "))
        assertFalse(StringUtils.isNullOrBlank("Hello World"))
    }

    @Test
    fun throwableFormat() {
        assertTrue(StringUtils.throwableFormat(IOException("IOException")).isNotEmpty())
    }
}