package com.github.open_edgn.data.format

/**
 *   一个基于 （Key/Value） 关联的数据集合包装类
 */
interface IDataProperties : IDataBackups, IDataReader, IDataWriter {

    val keys: Set<String>


    override fun toString(): String

    override fun hashCode(): Int

    override fun equals(other: Any?): Boolean

    operator fun get(key: String): Any?

}