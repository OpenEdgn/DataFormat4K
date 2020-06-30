package com.github.openEdgn.dataFormat4K.prop

import com.github.openEdgn.dataFormat4K.args.ArgsField
import com.github.openEdgn.dataFormat4K.args.ArgsIgnore
import com.github.openEdgn.dataFormat4K.args.ArgsLoad

class Test {
    @ArgsLoad
    @ArgsField("--config","-c")
    private lateinit var config:String


    @ArgsIgnore
    private val version:String = "1.0"
}