package com.github.open_edgn.data.format.utils

import com.github.open_edgn.data.format.ArgsItem
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class ArgsReaderTest {
    @Test
    fun test() {
        val argsReader = ArgsReader(arrayOf("--config", "%{user.home}"), ArgsLoader::class)
        val argsType = argsReader.getArgsType(ArgsLoader::class)
        println(argsType)
    }

    data class ArgsLoader(
            @ArgsItem("/etc/profile")
            val config: String,
            @ArgsItem("%{user.dir}")
            val configPath: File,
            @ArgsItem("false")
            val debug: Boolean

    )
}