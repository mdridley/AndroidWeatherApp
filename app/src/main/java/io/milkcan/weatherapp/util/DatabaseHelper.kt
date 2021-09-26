package io.milkcan.weatherapp.util

import androidx.room.Database
import androidx.room.RoomDatabase
import io.milkcan.weatherapp.data.dao.WeatherDao
import io.milkcan.weatherapp.model.Forecast

@Database(
    entities = [
        Forecast::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseHelper: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}