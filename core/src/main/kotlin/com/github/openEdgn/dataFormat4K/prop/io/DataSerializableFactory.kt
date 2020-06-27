package com.github.openEdgn.dataFormat4K.prop.io

import com.github.openEdgn.dataFormat4K.prop.data.PropData
import javafx.scene.input.DataFormat
import org.slf4j.LoggerFactory
import java.io.*
import java.lang.Exception
import java.lang.NullPointerException
import java.lang.RuntimeException
import java.net.URLDecoder
import java.util.*
import java.util.regex.Pattern


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
    fun input(reader: Reader, container: (String, PropData) -> Unit): Long

    /**
     * 将容器下的数据序列化输出到 write 下
     *
     * @param container Map<String, Any> 容器
     * @param writer Writer 接收的接口
     * @return Long 序列化数据的数目
     */
    fun output(container: Map<String, PropData>, writer: Writer): Long

    companion object {
        @Volatile
        var defaultFactory: DataSerializableFactory = SimpleDataSerializableFactory()
    }

    class SimpleDataSerializableFactory : DataSerializableFactory {
        private val logger = LoggerFactory.getLogger(javaClass)
        private val nameRegex = Regex("(?<=<name>).+(?=</name>)")
        private val typeRegex = Regex("(?<=<type>).+(?=</type>)")
        private val valueRegex = Regex("(?<=<value>).+(?=</value>)")

        override fun input(reader: Reader, container: (String, PropData) -> Unit): Long {
            val bufferedReader = BufferedReader(reader)
            bufferedReader.lines().forEach {
                try {
                    val key = (nameRegex.find(it)
                            ?: throw NullPointerException("未发现<name></name>.")).groupValues[0]
                    val type = (typeRegex.find(it)
                            ?: throw NullPointerException("未发现<type></type>.")).groupValues[0]
                    val value = (valueRegex.find(it)
                            ?: throw NullPointerException("未发现<value></value>.")).groupValues[0]
                } catch (e: Exception) {

                }
            }
            return 0
        }

        override fun output(container: Map<String, PropData>, writer: Writer): Long {
            val printWriter = PrintWriter(writer, true)
            printWriter.println("<!-- 并非XML解析，每一条数据仅占一行，请保持固定格式-->")
            container.forEach { (t, u) ->
                printWriter.println("<data>" +
                        "<name>${dataFormat(t)}</name>" +
                        "<type>${u.type.name}</type>" +
                        "<value>${dataFormat(u.data)}</value>" +
                        "</data>")

            }
            return container.size.toLong()
        }


        private fun dataFormat(t: String): String {
            return URLDecoder.decode(t, "utf-8")
        }

    }
}