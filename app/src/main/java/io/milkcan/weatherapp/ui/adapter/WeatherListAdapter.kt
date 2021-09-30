package io.milkcan.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import io.milkcan.weatherapp.R
import io.milkcan.weatherapp.model.entity.WeatherForecast

class WeatherListAdapter(
    private val items: ArrayList<WeatherForecast>,
    private val listener: WeatherItemClicked
    ): RecyclerView.Adapter<WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        val viewHolder = WeatherViewHolder(view)

        view.setOnClickListener {
            val position: Int = viewHolder.bindingAdapterPosition
            if (position > 0) {
                listener.onItemClicked(items[position])
            }
        }

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

interface WeatherItemClicked {
    fun onItemClicked(item: WeatherForecast)
}