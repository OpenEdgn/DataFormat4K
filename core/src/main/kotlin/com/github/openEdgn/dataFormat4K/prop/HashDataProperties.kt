package com.github.openEdgn.dataFormat4K.prop

import com.github.openEdgn.dataFormat4K.prop.format.DataFormatFactory
import java.io.Reader
import java.io.Writer
import java.lang.NullPointerException
import java.lang.StringBuilder
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.regex.Pattern


class HashDataProperties : BaseDataProperties() {

    private val hashMap = HashMap<String, Any>()
    private val readWriteLock = ReentrantReadWriteLock()
    private val readLock = readWriteLock.readLock()
    private val writeLock = readWriteLock.writeLock()


    override fun importData(properties: Reader, cover: Boolean): Long {
        return writeLock.lock {
            DataFormatFactory.defaultFactory.format(properties) { k, v ->
                set0(k, v)
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

    override fun <T : Any> get(key: String): T? {
        try {
            readLock.lock {
                val any = hashMap[key]
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
        hashMap[key.trim().toUpperCase()] = value
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
            hashMap.containsKey(key.trim().toUpperCase())
        }
    }

    override fun replace(key: String, value: Any): Boolean {
        return readLock.lock {
            if (hashMap.containsKey(key).not()) {
                false
            } else {
                hashMap.remove(key)
                set0(key, value)
                true
            }
        }
    }

    override fun <T : Any> getValue(key: String): T? {
        return get(key)
    }

    override fun <T : Any> getValueOrDefault(key: String, defaultValue: T): T {
        return getValue(key) ?: defaultValue
    }

    override fun getString(key: String): String? {

        val result = super.getString(key)
        return if (result == null) {
            null
        } else {
            formatString(result)
        }
    }


    override fun getStringOrDefault(key: String, defaultValue: String): String {
        return formatString(super.getStringOrDefault(key, defaultValue))
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

    private val regex = Regex("%\\{.+?}")
    private val spit = Regex("(^%\\{|}$)")

    private fun formatString(result: String): String {
        val compile = Pattern.compile(regex.pattern)
        val matcher = compile.matcher(result)
        val stringBuilder = StringBuilder()
        stringBuilder.append(result)
        while (matcher.find()) {
            val data = matcher.group()
            val formatItem = data.split(spit)[1]
            if (containsKey(formatItem)) {
                stringBuilder.replace(Regex(formatItem) ,getStringOrDefault(formatItem,data))
            } else if (System.getProperties().containsKey(formatItem.trim().toUpperCase())) {
                stringBuilder.replace(Regex(formatItem) ,System.getProperty(formatItem,data))
            }
        }
        return stringBuilder.toString()
    }


}