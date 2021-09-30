package io.milkcan.weatherapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.milkcan.weatherapp.model.api.WeatherForecast

@Entity
class WeatherForecast {
    @PrimaryKey
    var locationId: Int = 0
    var name: String = ""

    companion object {
        fun convertApiToEntity(apiData: WeatherForecast): io.milkcan.weatherapp.model.entity.WeatherForecast {
            val entity = WeatherForecast()
            entity.locationId = apiData.id
            entity.name = apiData.name
            return entity
        }
    }
}