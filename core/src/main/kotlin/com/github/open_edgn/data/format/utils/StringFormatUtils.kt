package com.github.open_edgn.data.format.utils

object StringFormatUtils{

    private val formatMap: HashMap<Class<*>, IObjectFormat<*>> = HashMap()

    init {
        val simpleObjectFormat: List<IObjectFormat<*>> = listOf(
                ByteObjectFormat(),
                FloatObjectFormat(),
                IntegerObjectFormat(),
                LongObjectFormat(),
                ShortObjectFormat(),
                DoubleObjectFormat(),
                BooleanObjectFormat(),
                CharObjectFormat(),
                StringObjectFormat()
        )
        for (format in simpleObjectFormat) {
            for (clazz in format.register) {
                formatMap[clazz] = format
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> parse(data: String, clazz: Class<T>): T {
        var format = formatMap[clazz]
        if (format == null) {
            for (key in formatMap.keys) {
                if (key.isAssignableFrom(clazz)) {
                    format = formatMap[key]
                    break
                }
            }
        }
        if (format == null) {
            throw NotFoundException("无法找到解析 [ ${clazz.simpleName} ] 的方案.")
        } else {
            return format.parse(data) as T
        }
    }
}

