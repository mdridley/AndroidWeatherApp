package io.milkcan.weatherapp.data.repository

import io.milkcan.weatherapp.data.api.NetworkingSingleton
import io.milkcan.weatherapp.data.api.OpenWeatherAPI
import io.milkcan.weatherapp.data.cache.WeatherCache
import io.milkcan.weatherapp.model.entity.WeatherForecast
import io.milkcan.weatherapp.ui.activity.MainActivity
import io.milkcan.weatherapp.util.Settings.API_KEY
import io.milkcan.weatherapp.util.Settings.CACHE_TIMEOUT
import io.milkcan.weatherapp.util.Settings.UNITS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WeatherRepository private constructor(): CoroutineScope
{
    companion object {
        private var weatherRepository: WeatherRepository? = null

        val instance: WeatherRepository
            @Synchronized get() {
                if (weatherRepository == null) {
                    weatherRepository = WeatherRepository()
                }

                return weatherRepository!!
            }
    }

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val service by lazy { NetworkingSingleton.instance.createService(OpenWeatherAPI::class.java) }
    private val weatherDao = MainActivity.database.weatherDao()

    fun getCurrentWeather(locationId: Int): Flow<WeatherForecast> {
        launch {
            refreshWeather(locationId)
        }
        return weatherDao.load(locationId)
    }

    private suspend fun refreshWeather(forecastId: Int) {
        val weatherExists = WeatherCache.instance.has(forecastId, CACHE_TIMEOUT)
        if (weatherExists == null) {
            val response = service.getCurrentWeather(forecastId.toString(), UNITS, API_KEY)
            val entity = WeatherForecast.convertApiToEntity(response)
            weatherDao.save(entity)
            WeatherCache.instance.set(entity)
        }
    }
}