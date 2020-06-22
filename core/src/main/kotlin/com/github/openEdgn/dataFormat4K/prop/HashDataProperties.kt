package com.github.openEdgn.dataFormat4K.prop

import com.github.openEdgn.dataFormat4K.prop.dataFill.DataFillFactory
import com.github.openEdgn.dataFormat4K.prop.format.DataFormatFactory
import java.io.Reader
import java.io.Writer
import java.util.HashMap
import java.lang.NullPointerException
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantReadWriteLock


class HashDataProperties(private val ignoreKeyCase: Boolean = true) : BaseDataProperties() {

    private val hashMap = HashMap<String, Any>()
    private val readWriteLock = ReentrantReadWriteLock()
    private val readLock = readWriteLock.readLock()
    private val writeLock = readWriteLock.writeLock()


    override fun importData(properties: Reader, cover: Boolean): Long {
        return writeLock.lock {
            DataFormatFactory.defaultFactory.format(properties) { k, v ->
                if (!containsKey(k) || cover) {
                    set0(k, v)
                }
            }
        }
    }

    override fun exportData(writer: Writer): Long {
        return readLock.lock {
            DataFormatFactory.defaultFactory.output(hashMap, writer)
        }
    }

    override fun remove(key: String): Long {
        return writeLock.lock {
            val k = keyFormat(key)
            if (hashMap.containsKey(k)) {
                hashMap.remove(k)
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

    override fun <T : Any> get(key: String): T? {
       return get0(key)
    }

    private fun <T> get0(key: String): T? {
        try {
            readLock.lock {
                val any = hashMap[keyFormat(key)]
                if (any == null) {
                    throw NullPointerException()
                } else {
                    try {
                        return any as T
                    } catch (e: Throwable) {
                        throw NullPointerException(e.message)
                    }
                }
            }
        } catch (_: NullPointerException) {
            return null
        }
    }

    override fun set(key: String, value: Any) {
        writeLock.lock {
            set0(key, value)
        }
    }

    /**
     * 【非线程安全方法】 指定一条数据
     *
     * @param key String 键值
     * @param value Any 数据
     */
    private fun set0(key: String, value: Any) {
        hashMap[keyFormat(key)] = value
    }

    private fun keyFormat(key: String): String {
        return if (ignoreKeyCase) {
            key.trim().toUpperCase()
        } else {
            key
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
            hashMap.containsKey(keyFormat(key))
        }
    }

    override fun replace(key: String, value: Any): Boolean {
        return writeLock.lock {
            if (hashMap.containsKey(keyFormat(key)).not()) {
                false
            } else {
                hashMap.remove(keyFormat(key))
                set0(key, value)
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

    private fun formatString(source: String): String {
        val result = DataFillFactory.defaultValue.fill(source, hashMap, ignoreKeyCase)
        return DataFillFactory.defaultValue.fill(result, System.getProperties() as Map<String, Any>, false)
    }
}