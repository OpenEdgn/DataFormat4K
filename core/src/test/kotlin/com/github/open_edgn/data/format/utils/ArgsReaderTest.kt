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
        assertEquals(argsBean.logger.absolutePath,StringFillUtils.fillFormSystemProp("%{user.dir}"))

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
        @ArgsItem("%{user.dir}")
        lateinit var logger: File
    }

    @Test
    fun testSecond() {
        val test2 = ArgsReader(
                arrayOf("--work-dir","data/app", "-d", "--skip-args"),
                Test2::class

        ).getArgsBean(Test2::class)
        assertEquals(File("data/app"),test2.cfgPath)
        assertEquals(true,test2.debug)

    }
    class Test2{
        @ArgsItem(alias = ["work-dir","w"],defaultValue = "/bat/dir")
        lateinit var cfgPath:File
        @ArgsItem(alias = ["d"])
        var debug:Boolean = false

    }
}