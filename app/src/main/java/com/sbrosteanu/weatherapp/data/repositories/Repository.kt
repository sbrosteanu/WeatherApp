package com.sbrosteanu.weatherapp.data.repositories

import com.sbrosteanu.weatherapp.data.source.local.dao.CityEntity
import com.sbrosteanu.weatherapp.domain.dto.WeatherDetailsDTO
import io.reactivex.Flowable
import io.reactivex.Single

interface Repository {

    fun getWeather(cityName: String): Single<WeatherDetailsDTO>

    fun getCities(): Flowable<List<CityEntity>>

    fun addCity(cityName: String)

}