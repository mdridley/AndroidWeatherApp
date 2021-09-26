package io.milkcan.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.milkcan.weatherapp.model.City

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cities = mutableListOf<City>()
        cities.add(City(4076795, "Montgomery", "Alabama"))
        cities.add(City(5554072, "Juneau", "Alaska"))
        cities.add(City(5308655, "Phoenix", "Arizona"))
        cities.add(City(4119403, "Little Rock", "Arkansas"))
        cities.add(City(5389519, "Sacramento", "California"))

//        val currentForecasts = loadCapitalForecasts()
    }

//    private fun loadCapitalForecasts(): List<CurrentWeather> {

//    }
}
