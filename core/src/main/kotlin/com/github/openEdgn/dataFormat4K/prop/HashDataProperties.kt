package com.github.openEdgn.dataFormat4K.prop

import com.github.openEdgn.dataFormat4K.prop.BaseDataProperties.DataType.*
import com.github.openEdgn.dataFormat4K.prop.format.DataFormatFactory
import com.github.openEdgn.dataFormat4K.prop.io.DataSerializableFactory
import java.io.Reader
import java.io.Writer
import java.util.HashMap
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantReadWriteLock

class HashDataProperties : BaseDataProperties() {

    private val hashMap = HashMap<String, PropData>()
    private val readWriteLock = ReentrantReadWriteLock()
    private val readLock = readWriteLock.readLock()
    private val writeLock = readWriteLock.writeLock()


    override fun setValue(key: String, value: Any, dataType: DataType): Boolean {
        return writeLock.lock {
            when (dataType) {
                BYTE -> hashMap[key] = PropData((value as Byte).toString(), dataType)
                FLOAT -> hashMap[key] = PropData((value as Float).toString(), dataType)
                INTEGER -> hashMap[key] = PropData((value as Int).toString(), dataType)
                LONG -> hashMap[key] = PropData((value as Long).toString(), dataType)
                SHORT -> hashMap[key] = PropData((value as Short).toString(), dataType)
                DOUBLE -> hashMap[key] = PropData((value as Double).toString(), dataType)
                BOOLEAN -> hashMap[key] = PropData((value as Boolean).toString(), dataType)
                CHAR -> hashMap[key] = PropData((value as Char).toString(), dataType)
                STRING -> hashMap[key] = PropData(value as String, dataType)
                else -> {
                    logger.debug("未知数据类型")
                    return@lock false
                }
            }
            return@lock true
        }

    }

    override fun <T> getValue(key: String, dataType: DataType): T? {
        var result: T? = null
        readLock.lock {
            val propData = hashMap[key]
            if (propData != null) {
                if (propData.type != dataType) {
                    logger.debug("返回值类型和储存数据类型{}不匹配.", propData.type.clazz.simpleName)
                    result = null
                } else {
                    val data = propData.data
                    result = when (dataType) {
                        BYTE -> data.toByte()
                        FLOAT -> data.toFloat()
                        INTEGER -> data.toInt()
                        LONG -> data.toLong()
                        SHORT -> data.toShort()
                        DOUBLE -> data.toDouble()
                        BOOLEAN -> data.toBoolean()
                        CHAR -> data[0]
                        STRING -> data
                        else -> {
                            logger.debug("key {}为未知数据类型", key)
                            null
                        }
                    } as T
                    //此处已做完整的类型检查
                }
            } else {
                logger.debug("未找到 key 为{}的数据", key)
            }
            result = null

        }
        return result
    }


    override fun importData(properties: Reader, coverData: Boolean): Long {
        return writeLock.lock {
            DataSerializableFactory.defaultFactory.format(properties) { k, v ->
                if (!containsKey(k) || coverData) {
                    set(k, v)
                }
            }
        }
    }

    override fun exportData(writer: Writer): Long {
        return readLock.lock {
            DataSerializableFactory.defaultFactory.output(hashMap, writer)
        }
    }

    override fun remove(key: String): Long {
        return writeLock.lock {
            if (hashMap.containsKey(key)) {
                hashMap.remove(key)
                1
            } else {
                0
            }
        }
    }

    override fun removeAll(): Long {
        return writeLock.lock {
            val size = hashMap.size
            hashMap.clear()
            size.toLong()
        }
    }

    override fun toString(): String {
        return hashMap.toString()
    }

    override fun hashCode(): Int {
        return hashMap.hashCode()
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as HashDataProperties
        if (hashMap != other.hashMap) return false
        return true
    }

    override fun containsKey(key: String): Boolean {
        return readLock.lock {
            hashMap.containsKey(key)
        }
    }

    override fun replace(key: String, value: Any): Boolean {
        return writeLock.lock {
            if (hashMap.containsKey(key).not()) {
                false
            } else {
                hashMap.remove(key)
                set(key, value)
                true
            }
        }
    }

    override fun getString(key: String): String? {
        readLock.lock {
            val result = super.getString(key)
            return if (result == null) {
                null
            } else {
                formatString(result)
            }
        }
    }


    override fun getStringOrDefault(key: String, defaultValue: String): String {
        readLock.lock {
            return formatString(super.getStringOrDefault(key, defaultValue))
        }
    }
    private fun formatString(source: String): String {
        val result = DataFormatFactory.defaultValue.fill(source, hashMap, false)
        return DataFormatFactory.defaultValue.fill(result, System.getProperties() as Map<String, Any>, false)
    }
    /**
     * 读写锁方案
     *
     * @receiver Lock
     * @param lock Function0<T>
     * @return T
     */
    private inline fun <T : Any> Lock.lock(lock: () -> T): T {
        lateinit var result: T
        var throwable: Throwable? = null
        this.lock()
        try {
            result = lock()
        } catch (e: Throwable) {
            throwable = e
        } finally {
            this.unlock()
        }
        if (throwable != null) {
            throw throwable
        }
        return result
    }
}