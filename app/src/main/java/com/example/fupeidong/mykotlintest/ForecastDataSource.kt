package com.example.fupeidong.mykotlintest

import com.example.fupeidong.mykotlintest.domain.Domain

/**
 * Created by fupeidong on 2017/6/29.
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long , date: Long): Domain.ForecastList?
    fun requestDayForecast(id: Long): Domain.Forecast?
}