package io.milkcan.weatherapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CityForecast {
    @PrimaryKey
    var locationId: Int = 0

    companion object {
        fun convertApiToEntity(apiData: io.milkcan.weatherapp.model.api.CityForecast): CityForecast {
            val entity = CityForecast()
            entity.locationId = apiData.city.id
            return entity
        }
    }
}