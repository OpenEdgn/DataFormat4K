package com.github.open_edgn.data.format

import java.io.File

class ByteObjectFormat : IObjectFormat<Byte> {
    override val register: Array<Class<out Byte>> =
            arrayOf(Byte::class.java, Byte::class.javaObjectType)

    override fun parse(data: String): Byte {
        return data.toByte()
    }

    override fun format(data: Any): String {
        return data.toString()
    }
}

class ShortObjectFormat : IObjectFormat<Short> {
    override val register: Array<Class<out Short>> =
            arrayOf(Short::class.java, Short::class.javaObjectType)

    override fun parse(data: String): Short {
        return data.toShort()
    }

    override fun format(data: Any): String {
        return data.toString()
    }
}


class IntegerObjectFormat : IObjectFormat<Int> {
    override val register: Array<Class<out Int>> =
            arrayOf(Int::class.java, Int::class.javaObjectType)

    override fun parse(data: String): Int {
        return data.toInt()
    }

    override fun format(data: Any): String {
        return data.toString()
    }
}

class LongObjectFormat : IObjectFormat<Long> {
    override val register: Array<Class<out Long>> =
            arrayOf(Long::class.java, Long::class.javaObjectType)

    override fun parse(data: String): Long {
        return data.toLong()
    }

    override fun format(data: Any): String {
        return data.toString()
    }
}

class FloatObjectFormat : IObjectFormat<Float> {
    override val register: Array<Class<out Float>> =
            arrayOf(Float::class.java, Float::class.javaObjectType)

    override fun parse(data: String): Float {
        return data.toFloat()
    }

    override fun format(data: Any): String {
        return data.toString()
    }
}

class DoubleObjectFormat : IObjectFormat<Double> {
    override val register: Array<Class<out Double>> =
            arrayOf(Double::class.java, Double::class.javaObjectType)

    override fun parse(data: String): Double {
        return data.toDouble()
    }

    override fun format(data: Any): String {
        return data.toString()
    }
}

class BooleanObjectFormat : IObjectFormat<Boolean> {
    override val register: Array<Class<out Boolean>> =
            arrayOf(Boolean::class.java, Boolean::class.javaObjectType)

    override fun parse(data: String): Boolean {
        return data.toBoolean()
    }

    override fun format(data: Any): String {
        return data.toString()
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

    override fun format(data: Any): String {
        return data.toString()
    }
}

class StringObjectFormat : IObjectFormat<String> {
    override val register: Array<Class<out String>> =
            arrayOf(String::class.java, String::class.javaObjectType)

    override fun parse(data: String): String {
        return data
    }

    override fun format(data: Any): String {
        return data.toString()
    }
}

class FileObjectFormat : IObjectFormat<File> {
    override val register: Array<Class<out File>> =
            arrayOf(File::class.java, File::class.javaObjectType)

    override fun parse(data: String): File {
        return File(data)
    }

    override fun format(data: Any): String {
        if (data is File) {
            // 替换掉 Windows 下的反斜杠
            return data.absolutePath.replace("\\", "/")
        } else {
            throw FormatErrorException("data#${data::class.java.simpleName} 不是 File 类型.")
        }
    }
}