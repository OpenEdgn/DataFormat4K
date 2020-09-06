package com.github.open_edgn.data.format

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ObjectUtilsTest {

    object DataOne {
        var testA: String = ""
    }

    @Test
    fun createObject() {
        val newInstance = DataOne::class.newInstance<DataOne>(true)
        DataOne.testA = "newInstance"
        assertEquals(newInstance, DataOne)
    }

    data class Data2(val data: String)

    @Test
    fun createDataObjectUseSafeMode() {
        assertThrows(NoSuchMethodException::class.java) {
            Data2::class.newInstance<Data2>(false)
        }
    }

    @Test
    fun isNull() {
        assertFalse(ObjectUtils.isNull(Any()))
        assertTrue(ObjectUtils.isNull(null))
    }
}