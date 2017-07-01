package com.example.fupeidong.mykotlintest.model

/**
 * Created by fupeidong on 2017/6/28.
 */
class DayForecast (var map: MutableMap<String, Any?>){
    var _id: Long by map
    var _date: String by map
    var _description: String by map
    var _high: Int by map
    var _low: Int by map
    var _iconUrl: String by map
    var _cityId: Long by map

    constructor(date: String, description: String, high: Int, low: Int, iconUrl: String, cityId: Long): this(HashMap()){
        this._date = date
        this._description = description
        this._high = high
        this._low = low
        this._iconUrl = iconUrl
        this._cityId = cityId
    }

}