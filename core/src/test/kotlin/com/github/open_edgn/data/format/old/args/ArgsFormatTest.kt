package com.github.open_edgn.data.format.old.args

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ArgsFormatTest {

    class TestA {
        @ArgsField("-c", "--config")
        lateinit var config: String

        @ArgsField("-d", "--debug")
        var debug: Boolean = false
    }

    private val argsFormat = ArgsFormat(TestA::class)


    fun loadArgs() {
        argsFormat.loadArgs(arrayOf("-c", "/etc/apt/source.list", "--debug"))
    }

    @Test
    fun getBeanByType() {
        loadArgs()
        val testA = argsFormat.getBeanByType(TestA::class)
        assertEquals(testA.config, "/etc/apt/source.list")
        assertEquals(testA.debug, true)
        printHelp()
    }


    private fun printHelp() {
        println(argsFormat.printHelp())
    }
}