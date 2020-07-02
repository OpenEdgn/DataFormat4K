package com.github.open_edgn.data.format.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class StringFormatUtilsTest {
    class DateObjectFormat : IObjectFormat<Date> {
        override val register: Array<Class<out Date>> =
                arrayOf(Date::class.java, Date::class.javaObjectType)

        override fun parse(data: String): Date {
            return Date(data.toLong())
        }

        override fun format(data: Any): String {
            if (data is Date) {
                return data.time.toString()
            } else {
                throw FormatErrorException("data#${data::class.java.simpleName} 不是 Date 类型.")
            }
        }
    }

    @Test
    fun include() {
        StringFormatUtils.include(DateObjectFormat())
        val timeMillis = System.currentTimeMillis()
        assertEquals(
                StringFormatUtils.parse(timeMillis.toString(), Date::class).time, timeMillis)
    }

    @Test
    fun parse() {
        assertEquals(StringFormatUtils.parse("1", Byte::class), 1)
        assertEquals(StringFormatUtils.parse("2", Short::class), 2)
        assertEquals(StringFormatUtils.parse("3", Int::class), 3)
        assertEquals(StringFormatUtils.parse("2523461237834183761", Long::class), 2523461237834183761L)
        assertEquals(StringFormatUtils.parse("2.3", Float::class), 2.3f)
        assertEquals(StringFormatUtils.parse("312.2113", Double::class), 312.2113)
        assertEquals(StringFormatUtils.parse("true", Boolean::class), true)
        assertEquals(StringFormatUtils.parse("C", Char::class), 'C')
        assertEquals(StringFormatUtils.parse("String Test", String::class), "String Test")
    }
        @Test
        fun format() {
            assertEquals(StringFormatUtils.format(1, Byte::class), "1")
            assertEquals(StringFormatUtils.format(2, Short::class), "2")
            assertEquals(StringFormatUtils.format(3, Int::class), "3")
            assertEquals(StringFormatUtils.format(1), "1")
            assertEquals(StringFormatUtils.format(2), "2")
            assertEquals(StringFormatUtils.format(3), "3")
        }
    }