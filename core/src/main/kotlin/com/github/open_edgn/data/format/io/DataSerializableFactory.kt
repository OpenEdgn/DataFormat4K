package com.github.open_edgn.data.format.io

import java.io.PrintWriter
import java.io.Reader
import java.io.Writer
import java.util.*


/**
 * 数据序列化工厂类
 */
interface DataSerializableFactory {
    /**
     * 将 Reader 流中的数据格式化成 KEY-VALUE 形式并放在 container 中
     * @param reader Reader 被取出容器
     * @param container Function2<String, PropData, Unit> 放入的容器 （非线程安全）
     * @return Long 格式化后的数目
     */
    fun input(reader: Reader, container: (String, String) -> Unit): Int

    /**
     * 将容器下的数据序列化输出到 write 下
     *
     * @param container Map<String, String> 容器
     * @param writer Writer 接收的接口
     * @return Long 序列化数据的数目
     */
    fun output(container: Map<String, String>, writer: Writer): Int

    companion object {
        @Volatile
        var defaultFactory: DataSerializableFactory = SimpleDataSerializableFactory()
    }

    class SimpleDataSerializableFactory : DataSerializableFactory {
        override fun input(reader: Reader, container: (String, String) -> Unit): Int {
            val properties = Properties()
            properties.load(reader)
            var len = 0
            properties.forEach { t, u ->
                len++
                container(t.toString(), u.toString())
            }
            return len
        }

        override fun output(container: Map<String, String>, writer: Writer): Int {
            val printWriter = PrintWriter(writer.toString())
            var len = 0
            container.forEach { (k, v) ->
                len++
                printWriter.println("${char2Unicode(k)}=${char2Unicode(v)}")
            }
            return len
        }

        private fun char2Unicode(k: String): String {
            val unicode = StringBuffer()
            for (c in k) {
                if (c.isLetterOrDigit() || c.isJavaIdentifierStart()) {
                    unicode.append(c)
                } else {
                    unicode.append("\\u").append(c.toLong().toString(16))
                }
            }
            return unicode.toString()
        }
    }
}