package io.milkcan.weatherapp.model.api

data class ApiResponseObject<out T>(
    val data: T,
    val status: String
)