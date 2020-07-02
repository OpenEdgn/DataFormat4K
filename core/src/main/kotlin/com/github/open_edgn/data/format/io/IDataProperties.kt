package com.github.open_edgn.data.format.io

interface IDataProperties : IDataBackups, IDataReader, IDataWriter {

    override fun toString(): String

    override fun hashCode(): Int

    override fun equals(other: Any?): Boolean

}