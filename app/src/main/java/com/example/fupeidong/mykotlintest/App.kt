package com.example.fupeidong.mykotlintest

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by fupeidong on 2017/7/1.
 */
class App : Application() {
    companion object {
        var instance: App by Delegates.notNull()
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}