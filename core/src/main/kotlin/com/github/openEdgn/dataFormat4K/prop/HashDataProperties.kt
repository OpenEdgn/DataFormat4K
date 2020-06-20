package com.github.openEdgn.dataFormat4K.prop

import java.io.BufferedReader
import java.io.Reader
import java.io.Writer
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantReadWriteLock


class HashDataProperties : BaseDataProperties() {

    private val hashMap = HashMap<String, Any>();
    private val readWriteLock = ReentrantReadWriteLock()
    private val readLock = readWriteLock.readLock()
    private val writeLock = readWriteLock.writeLock()


    override fun importData(properties: Reader, cover: Boolean): Long {
        return writeLock.lock {
            val bufferedReader = BufferedReader(properties)
            bufferedReader.lines().forEach {
                val propArr = it.split(Regex("="), 2)
                if (propArr.size != 2) {
                    logger.warn("data [$it] format error. ")
                } else {
                    set0(propArr[0], propArr[1]);
                }
            }
            1L
        }
    }

    override fun exportData(writer: Writer): Long {
        TODO("Not yet implemented")
    }

    override fun remove(key: String): Long {
        TODO("Not yet implemented")
    }

    override fun removeAll(): Long {
        TODO("Not yet implemented")
    }

    override fun <T : Any> get(key: String): T? {
        TODO("Not yet implemented")
    }

    override fun set(key: String, value: Any) {
        writeLock.lock {
            set0(key, value)
        }
    }

    private fun set0(key: String, value: Any) {
        hashMap[key.trim().toUpperCase()] = value
    }

    override fun toString(): String {
        TODO("Not yet implemented")
    }

    override fun hashCode(): Int {
        return hashMap.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsKey(key: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun replace(key: String, value: Any): Boolean {
        TODO("Not yet implemented")
    }

    override fun <T : Any> getValue(key: String): T? {
        TODO("Not yet implemented")
    }

    override fun <T : Any> getValueOrDefault(key: String, defaultValue: T): T {
        TODO("Not yet implemented")
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
}