package io.milkcan.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Forecast {
    @PrimaryKey var id: Int = 0
    var name: String = ""

    inner class Main {
        var temp: Double = 0.0
        var temp_min: Double = 0.0
        var temp_max: Double = 0.0
        var humidity: Int = 0
    }

    inner class Weather {
        var id: Int = 0
        var main: String = ""
        var description: String = ""
        var icon: String = ""
    }
}