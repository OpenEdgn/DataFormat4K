package com.github.open_edgn.data.format.old.factory

import java.lang.StringBuilder
import java.util.regex.Pattern
import kotlin.collections.HashSet


interface StringFormatFactory {

    /**
     * 格式化字符串数据
     *
     * @param source String 被格式化的数据
     * @param fillItems Hashtable<String, Any>
     * @param ignoreCase Boolean 是否忽略大小写 （如果 为 true 那么需要让 fillItems 的 KEY 为大写）
     * @return String 格式化后的数据
     */
    fun fill(source: String, fillItems: Map<String, Any>, ignoreCase: Boolean = false): String


    companion object {
        @Volatile
        var defaultValue: StringFormatFactory = SimpleStringFormatFactory()

    }

    class SimpleStringFormatFactory : StringFormatFactory {
        private val regex = Regex("%\\{.+?}")
        private val spit = Regex("(^%\\{|}$)")
        private val pattern = Pattern.compile(regex.pattern)


        override fun fill(source: String, fillItems: Map<String, Any>, ignoreCase: Boolean): String {
            val container = StringBuilder()
            container.append(source)
            val keySet = HashSet<String>()
            fill0(keySet, container, fillItems, ignoreCase)
            keySet.clear()
            return container.toString()
        }

        private fun fill0(keySet: HashSet<String>, container: StringBuilder, fillItems: Map<String, Any>, ignoreCase: Boolean) {
            val matcher = pattern.matcher(container.toString())
            while (matcher.find()) {
                val data = matcher.group()
                val key = data.split(spit)[1].run {
                    if (ignoreCase) {
                        this.toUpperCase()
                    } else {
                        this
                    }
                }
                if (keySet.contains(key)) {
                    continue
                    //出现相同字段，自动剔除，防止无线循环
                }
                val propData = fillItems[key]
                if (propData != null)
                    container.replace(0, container.length, container.toString().replace(data, propData.toString()))
                keySet.add(key)
            }
        }
    }
}