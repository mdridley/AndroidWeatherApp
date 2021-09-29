package io.milkcan.weatherapp.data.cache

import io.milkcan.weatherapp.model.WeatherForecast
import java.time.Duration
import java.time.LocalDate
import kotlin.collections.HashMap

class WeatherCache private constructor() {
    companion object {
        private var weatherCache: WeatherCache? = null

        val instance: WeatherCache
            @Synchronized get() {
                if (weatherCache == null) {
                    weatherCache = WeatherCache()
                }
                return weatherCache!!
            }
    }

    private var forecasts: HashMap<Int, CacheObject<WeatherForecast>> = HashMap()

    fun has(forecastId: Int, timeout: Long): WeatherForecast? {
        if (forecasts.containsKey(forecastId)) {
            val forecastCache = forecasts[forecastId]!!
            val elapsed: Long = Duration.between(LocalDate.now(), forecastCache.timestamp).toMillis()

            if (elapsed < timeout) {
                return forecastCache.cacheObject
            }
        }
        return null
    }

    fun set(weatherForecast: WeatherForecast) {
        val newObject: CacheObject<WeatherForecast> = CacheObject()
        newObject.cacheObject = weatherForecast
        forecasts[weatherForecast.id] = newObject
    }
}