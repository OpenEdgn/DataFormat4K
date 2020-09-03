package com.github.open_edgn.data.format

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class Args2ReaderTest {
    @BeforeEach
    fun before() {
        System.setProperty("dataFormat4K.debug", "true")
    }

    data class TestDataA(
            @ArgsItem("%{user.dir}", false, "A", "B")
            val dataA: String,
            @Ignore
            val dataB: String
    )

    class TestDataB {
        @ArgsItem("%{user.dir}", false, "a", "b")
        lateinit var dataA: String

        @Ignore
        lateinit var dataB: String

    }

    @Test
    fun testA() {
        val args2Reader = Args2Reader(arrayOf("--a=c", "--a=b"), false)
        val argsBean = args2Reader.getArgsBean(TestDataA::class)
    }
}