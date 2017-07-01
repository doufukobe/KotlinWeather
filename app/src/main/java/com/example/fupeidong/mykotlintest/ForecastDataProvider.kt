package com.example.fupeidong.mykotlintest

import com.example.fupeidong.mykotlintest.db.ForecastDb
import com.example.fupeidong.mykotlintest.domain.Domain

/**
 * Created by fupeidong on 2017/6/29.
 */
class ForecastDataProvider(val source: List<ForecastDataSource> = ForecastDataProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    inline fun <T, R : Any> Iterable<T>.firstResult(predicate: (T) -> R?) : R {
        for (element in this){
            val result = predicate(element)
            if (result != null) return result
        }
        throw NoSuchElementException("No element matching predicate was found.")
    }

    fun requestByZipCode (zipCode: Long, days: Int) : Domain.ForecastList {
        return source.firstResult{ requestSource(it, days, zipCode)}
    }

    private fun requestSource(source: ForecastDataSource, days: Int,
                              zipCode: Long): Domain.ForecastList? {

        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (res != null && res.dailyForecast.size >= days) res else null
    }

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T
            = source.firstResult { f(it) }

    fun requestForecast(id: Long): Domain.Forecast = requestToSources {
        it.requestDayForecast(id)
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLS * DAY_IN_MILLS
}