package io.milkcan.weatherapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.milkcan.weatherapp.model.entity.CityForecast
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(cityForecast: CityForecast)

    @Query("SELECT * FROM cityForecast WHERE locationId = :locationId")
    fun load(locationId: Int): Flow<CityForecast>
}