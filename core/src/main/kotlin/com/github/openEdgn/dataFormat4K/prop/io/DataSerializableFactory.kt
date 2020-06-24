package com.github.openEdgn.dataFormat4K.prop.io

import com.github.openEdgn.dataFormat4K.prop.PropData
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
        var defaultFactory: DataSerializableFactory = TODO()
    }

    class SimpleDataSerializableFactory : DataSerializableFactory {
        override fun input(reader: Reader, container: (String, PropData) -> Unit): Long {
            TODO("Not yet implemented")
        }

        override fun output(container: Map<String, PropData>, writer: Writer): Long {
            val printWriter = PrintWriter(writer)
            val properties = Properties()
            container.forEach { t, u ->
                TODO("Not yet implemented")

            }
            return container.size.toLong()
        }

    }
}