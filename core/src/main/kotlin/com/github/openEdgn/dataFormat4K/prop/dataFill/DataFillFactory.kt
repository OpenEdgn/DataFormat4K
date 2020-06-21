package com.github.openEdgn.dataFormat4K.prop.dataFill

import java.lang.StringBuilder
import java.util.*
import kotlin.collections.HashSet

interface DataFillFactory {

    /**
     * 格式化字符串数据
     *
     * @param source String 被格式化的数据
     * @param fillItems Hashtable<String, Any>
     * @param ignoreCase Boolean
     * @return String
     */
    fun fill(source: String, fillItems: Hashtable<String, Any>, ignoreCase: Boolean = false): String


    companion object {
        @Volatile
        var defaultValue: DataFillFactory = TODO()

    }

    class SimpleDataFillFactory : DataFillFactory {
        private val regex = Regex("%\\{.+?}")
        private val spit = Regex("(^%\\{|}$)")

        override fun fill(source: String, fillItems: Hashtable<String, Any>, ignoreCase: Boolean): String {
            val container = StringBuilder();
            val keySet = HashSet<String>()
            fill0(keySet, container, source, fillItems, ignoreCase)
            return container.toString()
        }

        private fun fill0(keySet: HashSet<String>, container: StringBuilder, source: String, fillItems: Hashtable<String, Any>, ignoreCase: Boolean) {

        }
    }
}