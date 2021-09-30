package io.milkcan.weatherapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import io.milkcan.weatherapp.R
import io.milkcan.weatherapp.data.repository.WeatherRepository
import io.milkcan.weatherapp.model.City
import io.milkcan.weatherapp.model.entity.WeatherForecast
import io.milkcan.weatherapp.ui.adapter.WeatherItemClicked
import io.milkcan.weatherapp.ui.adapter.WeatherListAdapter
import io.milkcan.weatherapp.util.DatabaseHelper
import java.io.File

class MainActivity : AppCompatActivity(), WeatherItemClicked {
    companion object {
        @JvmStatic lateinit var database: DatabaseHelper
            private set

        @JvmStatic
        lateinit var gson: Gson
            private set

        @JvmStatic
        lateinit var appCacheDir: File
            private set
    }

    private lateinit var weatherListAdapter: WeatherListAdapter
    private var forecasts: ArrayList<WeatherForecast> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(this, DatabaseHelper::class.java, "weather-db")
            .fallbackToDestructiveMigration() // Cache only - allow wipes
            .build()

        gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

        appCacheDir = cacheDir

        weatherView.layoutManager = LinearLayoutManager(this)
        loadCapitalForecasts()
        weatherListAdapter = WeatherListAdapter(forecasts, this)
        weatherView.adapter = weatherListAdapter
    }

    private fun loadCapitalForecasts() {
        val cities = mutableListOf<City>()
        cities.add(City(4076795, "Montgomery", "Alabama"))
        cities.add(City(5554072, "Juneau", "Alaska"))
        cities.add(City(5308655, "Phoenix", "Arizona"))
        cities.add(City(4119403, "Little Rock", "Arkansas"))
        cities.add(City(5389519, "Sacramento", "California"))
        cities.add(City(-1, "Denver", "Colorado"))
        cities.add(City(-1, "Hartford", "Connecticut"))
        cities.add(City(-1, "Dover", "Delaware"))
        cities.add(City(-1, "Tallahassee", "Florida"))
        cities.add(City(-1, "Atlanta", "Georgia"))
        cities.add(City(-1, "Honolulu", "Hawaii"))
        cities.add(City(-1, "Boise", "Idaho"))
        cities.add(City(-1, "Springfield", "Illinois"))
        cities.add(City(-1, "Indianapolis", "Indiana"))
        cities.add(City(-1, "Des Moines", "Iowa"))
        cities.add(City(-1, "Topeka", "Kansas"))
        cities.add(City(-1, "Frankfort", "Kentucky"))
        cities.add(City(-1, "Baton Rouge", "Louisiana"))
        cities.add(City(-1, "Augusta", "Maine"))
        cities.add(City(-1, "Annapolis", "Maryland"))
        cities.add(City(-1, "Boston", "Massachusetts"))
        cities.add(City(-1, "Lansing", "Michigan"))
        cities.add(City(-1, "St Paul", "Minnesota"))
        cities.add(City(-1, "Jackson", "Mississippi"))
        cities.add(City(-1, "Jefferson City", "Missouri"))
        cities.add(City(-1, "Helena", "Montana"))
        cities.add(City(-1, "Lincoln", "Nebraska"))
        cities.add(City(-1, "Carson City", "Nevada"))
        cities.add(City(-1, "Concord", "New Hampshire"))
        cities.add(City(-1, "Trenton", "New Jersey"))
        cities.add(City(-1, "Santa Fe", "New Mexico"))
        cities.add(City(-1, "Albany", "New York"))
        cities.add(City(-1, "Raleigh", "North Carolina "))
        cities.add(City(-1, "Bismarck", "North Dakota "))
        cities.add(City(-1, "Columbus", "Ohio"))
        cities.add(City(-1, "Oklahoma City", "Oklahoma"))
        cities.add(City(-1, "Salem", "Oregon"))
        cities.add(City(-1, "Harrisburg", "Pennsylvania"))
        cities.add(City(-1, "Providence", "Rhode Island "))
        cities.add(City(-1, "Columbia", "South Carolina"))
        cities.add(City(-1, "Pierre", "South Dakota"))
        cities.add(City(-1, "Nashville", "Tennessee"))
        cities.add(City(-1, "Austin", "Texas"))
        cities.add(City(-1, "Salt Lake City", "Utah"))
        cities.add(City(-1, "Montpelier", "Vermont"))
        cities.add(City(-1, "Richmond", "Virginia"))
        cities.add(City(-1, "Olympia", "Washington"))
        cities.add(City(-1, "Charleston", "West Virginia "))
        cities.add(City(5261457, "Madison", "Wisconsin"))
        cities.add(City(-1, "Cheyenne", "Wyoming"))

        val liveForecasts: ArrayList<LiveData<WeatherForecast>> = ArrayList()
        cities.forEach {
            if (it.id > 0) { // Ignore cities I did not populate ids for
                val forecast = WeatherRepository.instance.getCurrentWeather(it.id).asLiveData()
                liveForecasts.add(forecast)
            }
        }
    }

    override fun onItemClicked(item: WeatherForecast) {
        //TODO: Load forecast
        Toast.makeText(this, "Ready to load forecast for ${item.name}", Toast.LENGTH_LONG).show()
    }
}
