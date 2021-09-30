package io.milkcan.weatherapp.data.repository

import io.milkcan.weatherapp.data.api.NetworkingSingleton
import io.milkcan.weatherapp.data.api.OpenWeatherAPI
import io.milkcan.weatherapp.data.cache.ForecastCache
import io.milkcan.weatherapp.data.cache.WeatherCache
import io.milkcan.weatherapp.model.entity.CityForecast
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

class ForecastRepository private constructor(): CoroutineScope
{
    companion object {
        private var forecastRepository: ForecastRepository? = null

        val instance: ForecastRepository
            @Synchronized get() {
                if (forecastRepository == null) {
                    forecastRepository = ForecastRepository()
                }

                return forecastRepository!!
            }
    }

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val service by lazy { NetworkingSingleton.instance.createService(OpenWeatherAPI::class.java) }
    private val forecastDao = MainActivity.database.forecastDao()

    fun getForecast(locationId: Int): Flow<CityForecast> {
        launch {
            refreshForecast(locationId)
        }
        return forecastDao.load(locationId)
    }

    private suspend fun refreshForecast(forecastId: Int) {
        val weatherExists = WeatherCache.instance.has(forecastId, CACHE_TIMEOUT)
        if (weatherExists == null) {
            val response = service.getForecast(forecastId.toString(), UNITS, API_KEY)
            val entity = CityForecast.convertApiToEntity(response)
            forecastDao.save(entity)
            ForecastCache.instance.set(entity)
        }
    }
}