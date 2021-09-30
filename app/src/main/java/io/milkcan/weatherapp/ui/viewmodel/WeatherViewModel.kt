package io.milkcan.weatherapp.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.milkcan.weatherapp.data.repository.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    weatherRepository: WeatherRepository
): ViewModel() {
    val locationId : Int = savedStateHandle["locationId"] ?:
    throw IllegalArgumentException("Missing Location Id")

    val weather = weatherRepository.getCurrentWeather(locationId).asLiveData()
}