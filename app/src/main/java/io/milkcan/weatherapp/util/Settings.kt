package io.milkcan.weatherapp.util

import java.util.concurrent.TimeUnit

object Settings {
//    API
    const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    const val API_KEY = "930cca4888ac1add83446e6e2235793d"
    const val UNITS = "imperial"

//    Cache
    var CACHE_TIMEOUT = TimeUnit.DAYS.toMillis(1)
}