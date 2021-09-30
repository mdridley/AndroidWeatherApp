package io.milkcan.weatherapp.data.cache

import io.milkcan.weatherapp.model.entity.CityForecast
import java.time.Duration
import java.time.LocalDate

class ForecastCache private constructor() {
    companion object {
        private var forecastCache: ForecastCache? = null

        val instance: ForecastCache
            @Synchronized get() {
                if (forecastCache == null) {
                    forecastCache = ForecastCache()
                }
                return forecastCache!!
            }
    }

    private var forecasts: HashMap<Int, CacheObject<CityForecast>> = HashMap()

    fun has(forecastId: Int, timeout: Long): CityForecast? {
        if (forecasts.containsKey(forecastId)) {
            val forecastCache = forecasts[forecastId]!!
            val elapsed: Long = Duration.between(LocalDate.now(), forecastCache.timestamp).toMillis()

            if (elapsed < timeout) {
                return forecastCache.cacheObject
            }
        }
        return null
    }

    fun set(cityForecast: CityForecast) {
        val newObject: CacheObject<CityForecast> = CacheObject()
        newObject.cacheObject = cityForecast
        forecasts[cityForecast.locationId] = newObject
    }
}