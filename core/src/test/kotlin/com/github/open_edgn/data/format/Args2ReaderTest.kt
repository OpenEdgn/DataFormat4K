package com.github.open_edgn.data.format

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Args2ReaderTest {
    @BeforeEach
    fun before() {
        System.setProperty("dataFormat4K.debug", "true")
    }

    data class TestDataA(
            @ArgsItem("c", false, "a", "B")
            val dataA: String,
            @Ignore
            val dataB: String
    )

    class TestDataB {
        @ArgsItem("", true, "loader")
        val dataA: String? = null

        @Ignore
        lateinit var dataB: String

    }

    @Test
    fun testA() {
        val args2Reader = Args2Reader(arrayOf("--a=c", "--a=b"), false)
        val argsBean = args2Reader.getArgsBean(TestDataA::class)
        assertEquals(argsBean.dataA, "b")
    }

    @Test
    fun testB() {
        val args2Reader = Args2Reader(arrayOf("--loader=import", "--a=b"), false)
        val argsBean = args2Reader.getArgsBean(TestDataB::class)
        assertEquals(argsBean.dataA, "import")
    }
}