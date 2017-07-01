package com.example.fupeidong.mykotlintest.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by fupeidong on 2017/6/25.
 */
class NotNullSingleValueVar<T>() : ReadWriteProperty<Any?, T>{

    private var value: T ?= null;

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("not initialized")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw  IllegalStateException(" already initialized")
    }
}