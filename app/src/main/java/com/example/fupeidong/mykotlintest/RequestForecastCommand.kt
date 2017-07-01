package com.example.fupeidong.mykotlintest

import com.example.fupeidong.mykotlintest.domain.DataMapper
import com.example.fupeidong.mykotlintest.domain.Domain

/**
 * Created by fupeidong on 2017/5/21.
 */
class RequestForecastCommand(private val zipCode: String, val forecastProvider: ForecastDataProvider = ForecastDataProvider())
    : Command<Domain.ForecastList>{

    companion object {
        val DAYS = 7
    }

    override fun execute(): Domain.ForecastList {
        return forecastProvider.requestByZipCode(zipCode.toLong(), DAYS)
    }
}