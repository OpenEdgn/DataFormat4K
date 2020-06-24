package com.github.openEdgn.dataFormat4K.prop;

enum class DataType(val clazz: Class<*>) {
    BYTE(Byte::class.java),
    FLOAT(Float::class.java),
    INTEGER(Int::class.java),
    LONG(Long::class.java),
    SHORT(SHORT::class.java),
    DOUBLE(DOUBLE::class.java),
    BOOLEAN(Boolean::class.java),
    CHAR(CHAR::class.java),
    STRING(String::class.java),
    UNKNOWN(Any::class.java);

    companion object {

        fun format(any: Any): DataType {
            values().forEach {
                if (it != UNKNOWN) {
                    if (any.javaClass.isAssignableFrom(it.clazz)) {
                        return it
                    }
                }
            }
            return UNKNOWN
        }
    }
}