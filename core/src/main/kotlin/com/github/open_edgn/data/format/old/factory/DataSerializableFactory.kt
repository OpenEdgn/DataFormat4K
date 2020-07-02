package com.github.open_edgn.data.format.old.factory

import com.github.open_edgn.data.format.old.data.DataItem
import com.github.open_edgn.data.format.old.enums.DataType
import org.slf4j.LoggerFactory
import java.io.*
import java.lang.Exception
import java.lang.NullPointerException
import java.net.URLDecoder


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
    fun input(reader: Reader, container: (String, DataItem) -> Unit): Int

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
        private val logger = LoggerFactory.getLogger(javaClass)
        override fun input(reader: Reader, container: (String, DataItem) -> Unit): Int {
            TODO("Not yet implemented")
        }

        override fun output(container: Map<String, String>, writer: Writer): Int {
            TODO("Not yet implemented")
        }


    }
}