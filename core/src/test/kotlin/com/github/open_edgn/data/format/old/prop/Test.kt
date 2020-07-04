package com.github.open_edgn.data.format.old.prop

import com.github.open_edgn.data.format.emptyDataProperties
import com.github.open_edgn.data.format.old.args.ArgApi
import com.github.open_edgn.data.format.old.args.ArgsApis
import com.github.open_edgn.data.format.old.args.ArgsField
import com.github.open_edgn.data.format.old.args.ArgsFormat
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class Test {
    private val logger  = LoggerFactory.getLogger(javaClass)
    @Test
    fun test() {
        val dataProperties = emptyDataProperties()
        dataProperties.putString("test.key","Java home : %{JAVA_HOME}")
        println(dataProperties.getString("test.key"))
        if (true) return
        val argsFormat = ArgsFormat(Config2::class)
        argsFormat.loadArgs(arrayOf("-l","%{user.dir}/etc/profile","-d"))
        println(argsFormat.getBeanByType(Config2::class).toString())
        println(argsFormat.printHelp())
    }


    data class Config2(
            @ArgsApis(
                    ArgApi("zh_CN", "指定配置文件的地址")
            )
            @ArgsField("-l","--log")
            var logPath:String = "",
            @ArgsApis(
                    ArgApi("zh_CN", "指定启动的用户")
            )
            @ArgsField("-u","--user")
            var userName:String = "",
            @ArgsField("-d","--debug")
            val debug:Boolean = false
    )
}