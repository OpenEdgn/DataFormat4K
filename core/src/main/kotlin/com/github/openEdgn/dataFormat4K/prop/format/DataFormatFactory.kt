package com.github.openEdgn.dataFormat4K.prop.format

import java.io.Reader
import java.io.Writer


/**
 * 数据格式化工厂类
 */
interface DataFormatFactory {
    /**
     * 将 Reader 流中的数据格式化成 KEY-VALUE 形式并放在 container 中
     * @param reader Reader 被取出容器
     * @param container Function2<String, Any, Unit> 放入的容器 （非线程安全）
     * @return Long 格式化后的数目
     */
    fun format(reader: Reader, container: (String, Any) -> Unit): Long

    /**
     * 将容器下的数据序列化输出到 write 下
     *
     * @param container Map<String, Any> 容器
     * @param writer Writer 接收的接口
     * @return Long 序列化数据的数目
     */
    fun output(container:Map<String,Any>,writer: Writer):Long

    companion object{
        @Volatile
        var defaultFactory :DataFormatFactory = TODO()
    }
}