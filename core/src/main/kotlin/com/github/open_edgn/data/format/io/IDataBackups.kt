package com.github.open_edgn.data.format.io

import java.io.Reader
import java.io.Writer

interface IDataBackups {
    /**
     * 从流中导入数据
     * @param properties Reader 字符流
     * @param coverData 如果键冲突是否覆盖
     * @return Int 导入的数据数目
     */
    fun importData(properties: Reader, coverData: Boolean): Int

    /**
     * 导出数据到流中
     * @param writer Writer 目标流出口
     * @return Int 导出的数据数目
     */
    fun exportData(writer: Writer): Int


}