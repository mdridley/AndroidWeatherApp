package io.milkcan.weatherapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import io.milkcan.weatherapp.R
import io.milkcan.weatherapp.model.City
import io.milkcan.weatherapp.model.Forecast
import io.milkcan.weatherapp.ui.adapter.WeatherListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var weatherListAdapter: WeatherListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherView.layoutManager = LinearLayoutManager(this)
        val forecasts = loadCapitalForecasts()
        weatherListAdapter = WeatherListAdapter(forecasts)
        weatherView.adapter = weatherListAdapter
    }

    private fun loadCapitalForecasts(): ArrayList<Forecast> {
        val cities = mutableListOf<City>()
        cities.add(City(4076795, "Montgomery", "Alabama"))
        cities.add(City(5554072, "Juneau", "Alaska"))
        cities.add(City(5308655, "Phoenix", "Arizona"))
        cities.add(City(4119403, "Little Rock", "Arkansas"))
        cities.add(City(5389519, "Sacramento", "California"))

        val forecasts = ArrayList<Forecast>()
        cities.forEach {
            val forecast = Forecast()
            forecast.name = it.name
            forecasts.add(forecast)
        }

        return forecasts
    }
}
