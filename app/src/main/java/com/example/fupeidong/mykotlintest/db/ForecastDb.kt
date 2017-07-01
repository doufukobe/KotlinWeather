package com.example.fupeidong.mykotlintest.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.fupeidong.mykotlintest.ForecastDataSource
import com.example.fupeidong.mykotlintest.domain.DataMapper
import com.example.fupeidong.mykotlintest.domain.Domain
import com.example.fupeidong.mykotlintest.model.CityForecast
import com.example.fupeidong.mykotlintest.model.DayForecast
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by fupeidong on 2017/6/28.
 */
class ForecastDb : ForecastDataSource{

    val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance
    /**
     * 从数据库去读数据
     */
    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use{

        val dailyRequest = "${DayForecastTable.CITY_ID} = ?" + "AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList{ DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        if (city != null) convertToDomain(city) else null
    }

    /**
     * 通过id获取
     */
    override fun requestDayForecast(id: Long): Domain.Forecast? = forecastDbHelper.use{
        val forecast = select(DayForecastTable.NAME).byId(id)
                .parseOpt{DayForecast(HashMap(it))}
        if (forecast != null) convertDayToDomain(forecast) else null

    }

    fun SelectQueryBuilder.byId(id: Long): SelectQueryBuilder
            = whereSimple("_id = ?", id.toString())
    /**
     * 往数据库写数据
     */
    fun saveForecast (forecast: Domain.ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }

    fun SQLiteDatabase.clear(tableName: String) {
        execSQL("delete from $tableName")
    }
    /**
     * 存数据库时使用的ContentValue
     */
    fun <K, V : Any> MutableMap<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
            map({ Pair(it.key, it.value!!) }).toTypedArray()

    fun <T : Any> SelectQueryBuilder.parseList(
            parser: (Map<String, Any?>) -> T): List<T> =

            parseList(object : MapRowParser<T> {
                override fun parseRow(columns: Map<String, Any?>): T {
                   return parser(columns)
                }
            })

    //扩展属性
    val <T> List<T>.lastIndex: Int
        get() = size -1


    fun <T : Any> SelectQueryBuilder.parseOpt(
            parser: (Map<String, Any?>) -> T) : T? =
            parseOpt(object : MapRowParser<T>{
                override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
            })

    fun  convertFromDomain(forecast: Domain.ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: Domain.Forecast) =
            with(forecast) {
                DayForecast(date, description, high, low, iconUrl, cityId)
            }

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        Domain.ForecastList(_id, _city, _country, daily)
    }

    private fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Domain.Forecast(_id, _date, _description, _high, _low, _iconUrl)
    }
}



