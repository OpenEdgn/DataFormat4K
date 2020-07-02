package com.github.open_edgn.data.format.old.factory

import com.github.open_edgn.data.format.io.HashDataProperties
import com.github.open_edgn.data.format.io.IDataProperties

interface DataPropertiesFactory {
    /**
     * 创建一个空的 IDataProperties
     * @return IDataProperties  空的 IDataProperties
     */
    fun createEmptyProperties(): IDataProperties


    companion object{
        /**
         * 默认的实现方法
         */
        @Volatile
        var defaultFactory : DataPropertiesFactory = SimpleDataPropertiesFactory()

    }
    class SimpleDataPropertiesFactory: DataPropertiesFactory {
        override fun createEmptyProperties(): IDataProperties = HashDataProperties()


    }
}