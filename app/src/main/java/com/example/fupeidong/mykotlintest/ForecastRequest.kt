package com.example.fupeidong.mykotlintest
import android.util.Log
import com.google.gson.Gson
import java.net.URL
import java.util.logging.Logger

/**
 * Created by fupeidong on 2017/5/19.
 */
public class ForecastRequest(val zipCode: String) {
    companion object {
        private val APP_ID = "91cb15f5834f3af2673b3af3c54130cb"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    fun execute(): ResponseClasses.ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL+ zipCode).readText()
        Log.d("response json ", forecastJsonStr)
        return Gson().fromJson(forecastJsonStr, ResponseClasses.ForecastResult::class.java)
    }
}