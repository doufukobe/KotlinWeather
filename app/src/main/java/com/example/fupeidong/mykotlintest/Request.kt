package com.example.fupeidong.mykotlintest

import android.util.Log
import java.net.URL

/**
 * Created by fupeidong on 2017/5/19.
 */
public class Request(val url: String) {
    public fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr);
    }
}