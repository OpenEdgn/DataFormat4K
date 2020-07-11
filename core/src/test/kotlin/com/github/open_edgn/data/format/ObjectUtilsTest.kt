package com.github.open_edgn.data.format

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class ObjectUtilsTest {

    @Test
    fun isNull() {
        assertFalse(ObjectUtils.isNull(Any()))
        assertTrue(ObjectUtils.isNull(null))
    }
}