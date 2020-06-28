package com.github.openEdgn.dataFormat4K.factory

import com.github.openEdgn.dataFormat4K.prop.HashDataProperties
import com.github.openEdgn.dataFormat4K.prop.IDataProperties

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