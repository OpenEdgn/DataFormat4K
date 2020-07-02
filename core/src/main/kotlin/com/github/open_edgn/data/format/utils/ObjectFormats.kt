package com.github.open_edgn.data.format.utils

class ByteObjectFormat : IObjectFormat<Byte> {
    override val register: Array<Class<out Byte>> =
            arrayOf(Byte::class.java, Byte::class.javaObjectType)

    override fun parse(data: String): Byte {
        return data.toByte()
    }
}

class FloatObjectFormat : IObjectFormat<Float> {
    override val register: Array<Class<out Float>> =
            arrayOf(Float::class.java, Float::class.javaObjectType)

    override fun parse(data: String): Float {
        return data.toFloat()
    }
}

class IntegerObjectFormat : IObjectFormat<Int> {
    override val register: Array<Class<out Int>> =
            arrayOf(Int::class.java, Int::class.javaObjectType)

    override fun parse(data: String): Int {
        return data.toInt()
    }
}

class LongObjectFormat : IObjectFormat<Long> {
    override val register: Array<Class<out Long>> =
            arrayOf(Long::class.java, Long::class.javaObjectType)

    override fun parse(data: String): Long {
        return data.toLong()
    }
}

class ShortObjectFormat : IObjectFormat<Short> {
    override val register: Array<Class<out Short>> =
            arrayOf(Short::class.java, Short::class.javaObjectType)

    override fun parse(data: String): Short {
        return data.toShort()
    }
}

class DoubleObjectFormat : IObjectFormat<Double> {
    override val register: Array<Class<out Double>> =
            arrayOf(Double::class.java, Double::class.javaObjectType)

    override fun parse(data: String): Double {
        return data.toDouble()
    }
}

class BooleanObjectFormat : IObjectFormat<Boolean> {
    override val register: Array<Class<out Boolean>> =
            arrayOf(Boolean::class.java, Boolean::class.javaObjectType)

    override fun parse(data: String): Boolean {
        return data.toBoolean()
    }
}

class CharObjectFormat : IObjectFormat<Char> {
    override val register: Array<Class<out Char>> =
            arrayOf(Char::class.java, Char::class.javaObjectType)

    override fun parse(data: String): Char {
        if (data.isEmpty()) {
            throw FormatErrorException("无法将空的字符串格式化成 Char 类型数据.")
        }
        return data[0]
    }
}

class StringObjectFormat : IObjectFormat<String> {
    override val register: Array<Class<out String>> =
            arrayOf(String::class.java, String::class.javaObjectType)

    override fun parse(data: String): String {
        return data
    }
}