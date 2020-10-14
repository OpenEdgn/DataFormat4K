package com.github.open_edgn.data.format

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class StringFillUtilsTest {

    @Test
    fun fillFromSystemProp() {
        val properties = System.getProperties()
        assertEquals(StringFillUtils.fillFromSystemProp("PATH:%{user.dir}"),
                "PATH:${properties["user.dir"]}")
    }

    @Test
    fun fill() {
        val map = mapOf(Pair("TEST1", "HHH"), Pair("TEST2", "HHH"), Pair("TEST3", "HHH"))
        assertEquals(StringFillUtils.fill("%{TEST1}%{TEST2}%{TEST3}", map), "HHHHHHHHH")
        assertEquals(StringFillUtils.fill("%{TEST1}%{TEST2}%{TEST3}%{TEST4}", map), "HHHHHHHHH%{TEST4}")
    }
}