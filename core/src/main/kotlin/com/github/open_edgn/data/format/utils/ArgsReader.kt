package com.github.open_edgn.data.format.utils

import com.github.open_edgn.data.format.FormatErrorException
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.jvmName


class ArgsReader(args: Array<String>, vararg argsBeans: KClass<*>) {
    init {
        for (bean in argsBeans) {
            load(bean, args)
        }
    }

    private fun load(bean: KClass<*>, args: Array<String>) {
        bean.declaredMemberProperties.forEach {
            println(it.name)
            println(it.annotations)
        }
    }
}
