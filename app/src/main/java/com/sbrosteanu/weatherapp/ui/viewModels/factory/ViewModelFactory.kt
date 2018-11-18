package com.sbrosteanu.weatherapp.ui.viewModels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sbrosteanu.weatherapp.data.repositories.Repository
import com.sbrosteanu.weatherapp.ui.viewModels.WeatherViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}