package com.github.open_edgn.data.format

import java.io.Reader
import java.io.Writer
import java.util.*
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 *  基于 HashMap 的多字段类型的数据包装类
 */
@Suppress("DuplicatedCode")
class HashDataProperties(private val formatString: Boolean = true) : BaseDataProperties() {
    private val container = HashMap<String, String>()
    private val readWriteLock = ReentrantReadWriteLock()
    private val readLock = readWriteLock.readLock()
    private val writeLock = readWriteLock.writeLock()

    override fun toString(): String {
        return container.toString()
    }

    override fun hashCode(): Int {
        return container.hashCode()
    }

    override val keys: Set<String>
        get() = container.keys.toSet()

    override fun get(key: String): Any? = container[key]

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as HashDataProperties
        if (container != other.container) return false
        return true
    }


    override fun importData(properties: Reader, coverData: Boolean): Int {
        return writeLock.lock {
            DataSerializableFactory.defaultFactory.input(properties) { k, v ->
                if (!containsKey(k) || coverData) {
                    set(k, v)
                }
            }
        }
    }

    override fun exportData(writer: Writer): Int {
        return readLock.lock {
            DataSerializableFactory.defaultFactory.output(container, writer)
        }
    }

    override fun getByte(key: String): Byte? {
        return readLock.lockNullResult {
            val value = container[key]
            if (value == null) {
                null
            } else {
                StringFormatUtils.parse(value, Byte::class)
            }
        }
    }


    override fun getInt(key: String): Int? {
        return readLock.lockNullResult {
            val value = container[key]
            if (value == null) {
                null
            } else {
                StringFormatUtils.parse(value, Int::class)
            }
        }
    }

    override fun getLong(key: String): Long? {
        return readLock.lockNullResult {
            val value = container[key]
            if (value == null) {
                null
            } else {
                StringFormatUtils.parse(value, Long::class)
            }
        }
    }

    override fun getFloat(key: String): Float? {
        return readLock.lockNullResult {
            val value = container[key]
            if (value == null) {
                null
            } else {
                StringFormatUtils.parse(value, Float::class)
            }
        }
    }

    override fun getShort(key: String): Short? {
        return readLock.lockNullResult {
            val value = container[key]
            if (value == null) {
                null
            } else {
                StringFormatUtils.parse(value, Short::class)
            }
        }
    }

    override fun getDouble(key: String): Double? {
        return readLock.lockNullResult {
            val value = container[key]
            if (value == null) {
                null
            } else {
                StringFormatUtils.parse(value, Double::class)
            }
        }
    }

    override fun getChar(key: String): Char? {
        return readLock.lockNullResult {
            val value = container[key]
            if (value == null) {
                null
            } else {
                StringFormatUtils.parse(value, Char::class)
            }
        }
    }

    override fun getString(key: String): String? {
        return readLock.lockNullResult {
            val value = container[key]
            if (value == null) {
                null
            } else {
                formatString(StringFormatUtils.parse(value, String::class))
            }
        }
    }


    override fun getStringOrDefault(key: String, defaultValue: String): String {
        readLock.lock {
            return formatString(super.getStringOrDefault(key, defaultValue))
        }
    }


    override fun getBoolean(key: String): Boolean? {
        return readLock.lockNullResult {
            val value = container[key]
            if (value == null) {
                null
            } else {
                StringFormatUtils.parse(value, Boolean::class)
            }
        }
    }

    override val length: Int
        get() = container.size

    override fun remove(key: String): Int {
        return writeLock.lock {
            if (container.containsKey(key)) {
                container.remove(key)
                1
            } else {
                0
            }
        }
    }

    override fun removeAll(): Int {
        return writeLock.lock {
            val size = container.size
            container.clear()
            size
        }
    }

    override fun <T> set(key: String, value: T): Boolean {
        return writeLock.lock {
            InternalLogger.printLogger(
                    javaClass,
                    InternalLogger.Type.DEBUG,
                    "添加数据(K=\'${key}\',V=\'${value}\');"
            )
            container[key] = StringFormatUtils.format(value as Any)
            true
        }
    }

    override fun containsKey(key: String): Boolean {
        return readLock.lock {
            container.containsKey(key)
        }
    }

    override fun replace(key: String, value: Any): Boolean {
        return writeLock.lock {
            if (container.containsKey(key).not()) {
                false
            } else {
                container.remove(key)
                set(key, value)
            }
        }
    }

    override fun putByte(key: String, value: Byte): IDataProperties {
        writeLock.lock {
            set(key, value)
        }
        return this
    }

    /**
     * 读写锁方案
     *
     * @receiver Lock
     * @param lock Function0<T>
     * @return T
     */
    private inline fun <T : Any?> Lock.lockNullResult(lock: () -> T): T? {
        var result: T? = null
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

    private fun formatString(source: String): String {
        if (!formatString) {
            return source
        }
        val result = StringFillUtils.fill(source, container)
        return StringFillUtils.fillFromSystemProp(result)
    }


}