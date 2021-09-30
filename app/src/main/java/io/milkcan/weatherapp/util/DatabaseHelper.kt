package io.milkcan.weatherapp.util

import androidx.room.Database
import androidx.room.RoomDatabase
import io.milkcan.weatherapp.data.dao.ForecastDao
import io.milkcan.weatherapp.data.dao.WeatherDao
import io.milkcan.weatherapp.model.entity.CityForecast
import io.milkcan.weatherapp.model.entity.WeatherForecast

@Database(
    entities = [
        WeatherForecast::class,
        CityForecast::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseHelper: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    abstract fun forecastDao(): ForecastDao
}