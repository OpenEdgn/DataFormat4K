package com.github.open_edgn.data.format

import com.github.open_edgn.data.format.utils.ArgsReader

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
annotation class Data(
        val list: String = ""
)

data class DataReader(
        @Data
        val load: String,
        @ArgsItem(alias = ["d"])
        var loadData: Int
)



class Main

fun main() {
    ArgsReader(arrayOf(), DataReader::class)
}

