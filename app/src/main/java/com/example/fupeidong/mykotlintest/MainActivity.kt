package com.example.fupeidong.mykotlintest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import kotlin.jvm.javaClass

class MainActivity : AppCompatActivity() {
    private val items = listOf("Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        niceToast("kotlin weather")
        weather_title.text = "Kotlin Weather"
        forecast_list.layoutManager = LinearLayoutManager(this)
        //forecast_list.setOnClickListener { toast("sdfs") }
       // val itemClick = MyClickListener(this)
        doAsync {
            val result = RequestForecastCommand("94043").execute()
            uiThread { forecast_list.adapter = ForecastListAdapter(result) {
                startActivity<DetailActivity>(DetailActivity.ID to it.id,
                        DetailActivity.CITY_NAME to result.city)
            } }
        }

        // forecast_list.adapter = ForecastListAdapter(items)
    }

    fun niceToast(message: String, tag: String = this.javaClass.simpleName, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, "[$tag] $message", length).show();
    }

//    class MyClickListener(val context: Context) : OnItemClickListener {
//        override fun invoke(forecase: Domain.Forecast) {
//            Toast.makeText(context, forecase.date, Toast.LENGTH_SHORT)
//        }
//    }
}
