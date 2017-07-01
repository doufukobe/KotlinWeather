package com.example.fupeidong.mykotlintest

import com.example.fupeidong.mykotlintest.db.ForecastDb
import com.example.fupeidong.mykotlintest.domain.DataMapper
import com.example.fupeidong.mykotlintest.domain.Domain

/**
 * Created by fupeidong on 2017/6/29.
 */
class ForecastServer(val dataMapper: DataMapper = DataMapper(), val forecastDb: ForecastDb = ForecastDb())
    : ForecastDataSource{
    override fun requestDayForecast(id: Long): Domain.Forecast? {
        throw UnsupportedOperationException()
    }

    override fun requestForecastByZipCode(zipCode: Long, date: Long): Domain.ForecastList? {

        val result = ForecastByZipCodeRequest(zipCode.toString())
        forecastDb.saveForecast(dataMapper.convertFromDataModel(zipCode, result))
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    fun ForecastByZipCodeRequest(zipCode: String): ResponseClasses.ForecastResult{
        val forecastRequest = ForecastRequest(zipCode)
        return forecastRequest.execute()
    }
}