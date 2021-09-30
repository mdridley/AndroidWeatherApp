package io.milkcan.weatherapp.data.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import io.milkcan.weatherapp.model.entity.WeatherForecast
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = REPLACE)
    fun save(weatherForecast: WeatherForecast)

    @Query("SELECT * FROM weatherForecast WHERE locationId = :locationId")
    fun load(locationId: Int): Flow<WeatherForecast>
}