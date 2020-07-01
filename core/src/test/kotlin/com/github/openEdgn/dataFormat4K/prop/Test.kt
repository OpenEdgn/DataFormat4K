package com.github.openEdgn.dataFormat4K.prop

import com.github.openEdgn.dataFormat4K.args.ArgsField
import com.github.openEdgn.dataFormat4K.args.ArgsFormat
import com.github.openEdgn.dataFormat4K.args.ArgsIgnore
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.io.File
import java.util.*

class Test {
    private val logger  = LoggerFactory.getLogger(javaClass)
    @Test
    fun test() {
        val argsFormat = ArgsFormat(Config2::class,Config3::class)
        argsFormat.load(arrayOf("-l","/etc/profile"))
        println(argsFormat.format(Config2::class).toString())

    }


    data class Config2(
            @ArgsField("-l","--log")
            var logPath:String = "")
    data class Config3(
            @ArgsField("-l","--log")
            var logPath:String = "")
}