package com.example.fupeidong.mykotlintest

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.fupeidong.mykotlintest.R.attr.color
import com.example.fupeidong.mykotlintest.domain.Domain
import com.squareup.picasso.Picasso
import org.jetbrains.anko.ctx
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlinx.android.synthetic.main.detail_layout.*

/**
 * Created by fupeidong on 2017/7/1.
 */
class DetailActivity : AppCompatActivity() {
    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_layout)
        title = intent.getStringExtra(CITY_NAME)
        doAsync {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
            uiThread { bindForecast(result) }
        }
    }

    private fun bindForecast(forecast: Domain.Forecast) {
        Picasso.with(ctx).load(forecast.iconUrl).into(icon)
        weatherDescription.text = forecast.description
        bindWeather(forecast.high to maxTemperature, forecast.low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}"
        it.second.setTextColor(color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        }))
    }

    public fun Context.color (res: Int) = ContextCompat.getColor(this,res)
}