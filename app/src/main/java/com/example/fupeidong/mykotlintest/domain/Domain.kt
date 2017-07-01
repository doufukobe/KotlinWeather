package com.example.fupeidong.mykotlintest.domain

/**
 * Created by fupeidong on 2017/5/21.
 */
public class Domain {
    data class ForecastList(val id: Long, val city: String, val country: String, val dailyForecast:List<Forecast>)

    data class Forecast(val id: Long, val date: String, val description: String, val high: Int, val low: Int, val iconUrl: String)
}