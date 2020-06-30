package com.github.openEdgn.dataFormat4K.prop

import com.github.openEdgn.dataFormat4K.args.ArgsField

class Test {
    @ArgsField(key = "config.path",alias = ["--config","-c"])
    private lateinit var config:String

}