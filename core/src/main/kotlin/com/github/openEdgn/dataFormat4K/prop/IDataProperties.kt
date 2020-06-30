package com.github.openEdgn.dataFormat4K.prop

import com.github.openEdgn.dataFormat4K.io.DataReader
import com.github.openEdgn.dataFormat4K.io.DataWriter
import java.io.Reader
import java.io.Writer

interface IDataProperties :DataReader,DataWriter{
    /**
     * 从流中导入数据
     * @param properties Reader 字符流
     * @param coverData 如果键冲突是否覆盖
     * @return Long 导入的数据数目
     */
    fun importData(properties: Reader, coverData: Boolean): Long

    /**
     * 导出数据到流中
     * @param writer Writer 目标流出口
     * @return Long 导出的数据数目
     */
    fun exportData(writer: Writer): Long

    override fun toString(): String

    override fun hashCode(): Int

    override fun equals(other: Any?): Boolean

}