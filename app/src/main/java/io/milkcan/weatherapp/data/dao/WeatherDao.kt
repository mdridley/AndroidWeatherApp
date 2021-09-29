package io.milkcan.weatherapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.milkcan.weatherapp.model.WeatherForecast

@Dao
interface WeatherDao {
    @Insert(onConflict = REPLACE)
    fun save(weatherForecast: WeatherForecast)

    @Query("SELECT * FROM weatherForecast WHERE id = :weatherForecastId")
    fun load(weatherForecastId: Int): WeatherForecast
}