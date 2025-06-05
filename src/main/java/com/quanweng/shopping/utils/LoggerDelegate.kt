package com.quanweng.shopping.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
*  使用方式：`val log by logger<xxx>()`
*/
class LoggerDelegate<in R : Any> : ReadOnlyProperty<R, Logger> {
    override fun getValue(thisRef: R, property: KProperty<*>): Logger {
        return LoggerFactory.getLogger(thisRef.javaClass)
    }
}
fun <R : Any> logger(): LoggerDelegate<R> = LoggerDelegate()
