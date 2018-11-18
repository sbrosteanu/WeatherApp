package com.sbrosteanu.weatherapp.ui.viewModels

import androidx.lifecycle.ViewModel
import com.sbrosteanu.weatherapp.data.repositories.Repository
import javax.inject.Inject

class WeatherViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    fun getWeather(cityName: String) = repository.getWeather(cityName = cityName)

    fun getCities() = repository.getCities()

    fun addCity(cityName: String) = repository.addCity(cityName = cityName)
}