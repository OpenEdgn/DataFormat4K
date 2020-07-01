package com.github.openEdgn.dataFormat4K.prop

import com.github.openEdgn.dataFormat4K.args.ArgApi
import com.github.openEdgn.dataFormat4K.args.ArgsApis
import com.github.openEdgn.dataFormat4K.args.ArgsField
import com.github.openEdgn.dataFormat4K.args.ArgsFormat
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class Test {
    private val logger  = LoggerFactory.getLogger(javaClass)
    @Test
    fun test() {
        val argsFormat = ArgsFormat(Config2::class)
        argsFormat.loadArgs(arrayOf("-l","%{user.dir}/etc/profile","-d"))
        println(argsFormat.getBeanByType(Config2::class).toString())
        println(argsFormat.printHelp())
    }


    data class Config2(
            @ArgsApis(
                    ArgApi("zh_CN","指定配置文件的地址")
            )
            @ArgsField("-l","--log")
            var logPath:String = "",
            @ArgsApis(
                    ArgApi("zh_CN","指定启动的用户")
            )
            @ArgsField("-u","--user")
            var userName:String = "",
            @ArgsField("-d","--debug")
            val debug:Boolean = false
    )
}