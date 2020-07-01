package com.github.openEdgn.dataFormat4K.prop

import com.github.openEdgn.dataFormat4K.args.ArgsField
import com.github.openEdgn.dataFormat4K.args.ArgsFormat
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class Test {
    private val logger  = LoggerFactory.getLogger(javaClass)
    @Test
    fun test() {
        val argsFormat = ArgsFormat(Config2::class,Config3::class)
        argsFormat.loadArgs(arrayOf("-l","%{user.dir}/etc/profile"))
        println(argsFormat.getBeanByType(Config2::class).toString())

    }


    data class Config2(
            @ArgsField("-l","--log")
            var logPath:String = "")
    data class Config3(
            @ArgsField("-l","--log")
            var logPath:String = "")
}