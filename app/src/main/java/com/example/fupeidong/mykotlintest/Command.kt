package com.example.fupeidong.mykotlintest

/**
 * Created by fupeidong on 2017/5/21.
 */
public interface Command<T> {

    fun execute(): T

}