package com.github.open_edgn.data.format.utils

import com.github.open_edgn.data.format.ArgsItem
import org.junit.jupiter.api.Test
import java.io.File

import org.junit.jupiter.api.Assertions.*

internal class ArgsReaderTest {
    @Test
    fun test() {
        val argsReader = ArgsReader(arrayOf("--config", "%{user.home}"), ArgsLoader::class, Data::class)
        val message = argsReader.getArgsBean(ArgsLoader::class)
        assertEquals(message.config, StringFillUtils.fillFormSystemProp("%{user.home}"))
        assertEquals(message.configPath.absolutePath, StringFillUtils.fillFormSystemProp("%{user.dir}"))
        val argsBean = argsReader.getArgsBean(Data::class)
        assertEquals(argsBean.logger.absolutePath,StringFillUtils.fillFormSystemProp("%{JAVA_HOME}"))

    }

    data class ArgsLoader(
            @ArgsItem("/etc/profile")
            val config: String,
            @ArgsItem("%{user.dir}")
            val configPath: File,
            @ArgsItem("false")
            val debug: Boolean
    )

    class Data {
        @ArgsItem("%{JAVA_HOME}")
        lateinit var logger: File
        override fun toString(): String {
            return "Data(logger='$logger')"
        }


    }
}