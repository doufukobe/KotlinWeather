package com.example.fupeidong.mykotlintest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.fupeidong.mykotlintest.domain.Domain
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import kotlinx.android.synthetic.main.recycler_item.*
import kotlinx.android.synthetic.main.recycler_item.view.*

/**
 * Created by fupeidong on 2017/5/19.
 */
class ForecastListAdapter(val weekForecast: Domain.ForecastList,
        val itemClick: (Domain.Forecast) -> Unit): RecyclerView.Adapter<ForecastListAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return weekForecast.dailyForecast.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast.dailyForecast[position])
    }

    class ViewHolder(view: View, val itemOnclick: (Domain.Forecast) -> Unit): RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast : Domain.Forecast) {
            with(forecast) {
                Picasso.with(itemView.context).load(iconUrl).into(itemView.icon)
                itemView.date.text = date
                itemView.description.text = description
                itemView.maxTemperature.text = "${high.toString()}"
                itemView.minTemperature.text = "${low.toString()}"
                itemView.setOnClickListener { itemOnclick(forecast) }
            }
        }

    }
}