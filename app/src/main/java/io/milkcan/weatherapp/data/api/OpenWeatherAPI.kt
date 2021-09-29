package io.milkcan.weatherapp.data.api

import io.milkcan.weatherapp.model.WeatherForecast
import io.milkcan.weatherapp.model.api.ApiResponseObject
import retrofit2.http.*
import io.reactivex.Single

interface OpenWeatherAPI {
    @GET("weather?q={locationId}&units=imperial&appid={apiKey}")
    suspend fun getCurrentWeather(@Path("locationId") locationId: Int, @Path("apiKey") apiKey: String): Single<ApiResponseObject<WeatherForecast>>

    @GET("forecast?q={locationId}&units=imperial&appid={apiKey}")
    suspend fun getForecast(@Path("locationId") locationId: Int, @Path("apiKey") apiKey: String): Single<ApiResponseObject<WeatherForecast>>
}