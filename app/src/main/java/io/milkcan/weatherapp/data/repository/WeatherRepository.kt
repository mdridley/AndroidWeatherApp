package io.milkcan.weatherapp.data.repository

import io.milkcan.weatherapp.App
import io.milkcan.weatherapp.data.api.NetworkingSingleton
import io.milkcan.weatherapp.data.api.OpenWeatherAPI
import io.milkcan.weatherapp.data.cache.WeatherCache
import io.milkcan.weatherapp.model.Forecast
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

class WeatherRepository  private constructor()
{
    companion object {
        const val API_KEY: String = "930cca4888ac1add83446e6e2235793d"
        val FRESH_TIMEOUT = TimeUnit.HOURS.toMillis(1)

        private var weatherRepository: WeatherRepository? = null

        val instance: WeatherRepository
            @Synchronized get() {
                if (weatherRepository == null) {
                    weatherRepository = WeatherRepository()
                }

                return weatherRepository!!
            }
    }

    private val service by lazy { NetworkingSingleton.instance.createService(OpenWeatherAPI::class.java) }
    private val weatherDao = App.database.weatherDao()

    suspend fun getCurrentWeather(locationId: Int): Flow<Forecast> {
        refreshWeather(locationId)
        return weatherDao.load(locationId)
    }

    private suspend fun refreshWeather(forecastId: Int) {
        val weatherExists = WeatherCache.instance.has(forecastId, FRESH_TIMEOUT)
        if (weatherExists == null) {
            service.getCurrentWeather(forecastId, API_KEY)
                .subscribeBy(
                    onSuccess = {
                        weatherDao.save(it.data)
                        WeatherCache.instance.set(it.data)
                    }
                )
        }
    }
}