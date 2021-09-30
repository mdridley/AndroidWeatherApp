package io.milkcan.weatherapp.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.milkcan.weatherapp.data.repository.ForecastRepository
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    forecastRepository: ForecastRepository
): ViewModel() {
    val locationId : Int = savedStateHandle["locationId"] ?:
    throw IllegalArgumentException("Missing Location Id")

    val forecast = forecastRepository.getForecast(locationId).asLiveData()
}