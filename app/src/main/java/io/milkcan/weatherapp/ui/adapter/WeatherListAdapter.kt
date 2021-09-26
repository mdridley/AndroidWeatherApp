package io.milkcan.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.milkcan.weatherapp.R
import io.milkcan.weatherapp.model.Forecast

class WeatherListAdapter(private val items: ArrayList<Forecast>): RecyclerView.Adapter<WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentItem = items[position]
        holder.title.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById(R.id.locationName)
}