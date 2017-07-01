package com.example.fupeidong.mykotlintest.domain

import com.example.fupeidong.mykotlintest.ResponseClasses
import java.text.DateFormat
import java.util.*
import com.example.fupeidong.mykotlintest.ResponseClasses.Forecast as ModelForecast
/**
 * Created by fupeidong on 2017/5/21.
 */
public class DataMapper {

    fun convertFromDataModel(city_id: Long, forecast: ResponseClasses.ForecastResult): Domain.ForecastList {
        return Domain.ForecastList(city_id, forecast.city.name, forecast.city.country,
                covertForecastListToDomain(forecast.list))
    }

    private fun covertForecastListToDomain(list: List<ModelForecast>): List<Domain.Forecast> {
        return list.map { convertForecastItemToDomain( it ) }
    }

    private fun convertForecastItemToDomain(forecast: ModelForecast): Domain.Forecast {
        return Domain.Forecast(forecast.id, convertDate(forecast.dt),
                forecast.weather[0].description, forecast.temp.max.toInt(),
                forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String {
        return "http://openweathermap.org/img/w/$iconCode.png"
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }
}