package com.github.open_edgn.data.format

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class ArgsReaderTest {
    @Test
    fun test() {
        val argsReader = ArgsReader(arrayOf("--config", "%{user.home}"), ArgsLoader::class, Data::class)
        val message = argsReader.getArgsBean(ArgsLoader::class)
        assertEquals(message.config, StringFillUtils.fillFromSystemProp("%{user.home}"))
        assertEquals(message.configPath.absolutePath, StringFillUtils.fillFromSystemProp("%{user.dir}"))
        val argsBean = argsReader.getArgsBean(Data::class)
        assertEquals(argsBean.logger.absolutePath, StringFillUtils.fillFromSystemProp("%{user.dir}"))

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
                arrayOf("--work-dir", "data/app", "-d", "--skip-args", "--debug"),
                Test2::class

        ).getArgsBean(Test2::class)
        assertEquals(File("data/app"), test2.cfgPath)
        assertEquals(true, test2.debug)

    }

    data class Test2(
            @ArgsItem(alias = ["work-dir", "w"], defaultValue = "/bat/dir")
            var cfgPath: File,
            @ArgsItem(alias = ["d", "debug"], defaultValue = "false")
            var debug: Boolean
    )

    @Test
    fun test3(): Unit {
        val argsBean = ArgsReader(
                arrayOf("--work-dir", "%{APPDATA}/PluginManager", " -d"),
                PluginManagerProperty::class).getArgsBean(PluginManagerProperty::class)
        println(argsBean)
    }

    data class PluginManagerProperty(
            /**
             * 工作目录
             */
            @ArgsItem(defaultValue = "%{user.dir}/Workspace", alias = ["work-dir"])
            var workDirectory: File,
            /**
             * 开启debug 模式
             */
            @ArgsItem(defaultValue = "false", alias = ["debug", "d"])
            val debug: Boolean
    )
}