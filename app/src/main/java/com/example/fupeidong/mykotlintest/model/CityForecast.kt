package com.example.fupeidong.mykotlintest.model

/**
 * Created by fupeidong on 2017/6/28.
 */
class CityForecast(val map: MutableMap<String, Any?>, val dailyForecast: List<DayForecast>) {

    var _id: Long by map
    var _city: String by map
    var _country: String by map

    constructor(id: Long, city: String, country: String, dailyForecast: List<DayForecast>): this(HashMap(), dailyForecast) {
        this._id = id
        this._city = city
        this._country = country
    }
}