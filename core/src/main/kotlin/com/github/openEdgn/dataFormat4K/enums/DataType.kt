package com.github.openEdgn.dataFormat4K.enums

enum class DataType(val clazz: Class<*>) {
    BYTE(Byte::class.javaObjectType),
    FLOAT(Float::class.javaObjectType),
    INTEGER(Int::class.javaObjectType),
    LONG(Long::class.javaObjectType),
    SHORT(Short::class.javaObjectType),
    DOUBLE(Double::class.javaObjectType),
    BOOLEAN(Boolean::class.javaObjectType),
    CHAR(Char::class.javaObjectType),
    STRING(String::class.javaObjectType),
    UNKNOWN(Any::class.java);

    companion object {

        fun format(any: Any): DataType {
            values().forEach {
                if (it != UNKNOWN) {
                    val javaClass = any.javaClass
                    val clazz = it.clazz
                    if (clazz.isAssignableFrom(javaClass)) {
                        return it
                    }
                }
            }
            return UNKNOWN
        }
    }
}