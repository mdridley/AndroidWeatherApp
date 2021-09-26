package io.milkcan.weatherapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.milkcan.weatherapp.model.Forecast
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = REPLACE)
    fun save(forecast: Forecast)

    @Query("SELECT * FROM forecast WHERE id = :forecastId")
    fun load(forecastId: Int): Flow<Forecast>
}