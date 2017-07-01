package com.example.fupeidong.mykotlintest

import com.example.fupeidong.mykotlintest.domain.Domain

/**
 * Created by fupeidong on 2017/7/1.
 */
class RequestDayForecastCommand(val id: Long,
                                val forecastProvider: ForecastDataProvider = ForecastDataProvider()) : Command<Domain.Forecast> {
    override fun execute() = forecastProvider.requestForecast(id)
}
