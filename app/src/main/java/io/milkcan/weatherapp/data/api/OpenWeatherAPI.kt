package io.milkcan.weatherapp.data.api

import io.milkcan.weatherapp.model.api.CityForecast
import io.milkcan.weatherapp.model.api.WeatherForecast
import retrofit2.http.*

interface OpenWeatherAPI {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("id") locationId: String,
        @Query("units") units: String,
        @Query("apiKey") apiKey: String,
    ): WeatherForecast

    @GET("forecast")
    suspend fun getForecast(
        @Query("id") locationId: String,
        @Query("units") units: String,
        @Query("apiKey") apiKey: String
    ): CityForecast
}