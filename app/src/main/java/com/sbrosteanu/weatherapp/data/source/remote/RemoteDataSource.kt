package com.sbrosteanu.weatherapp.data.source.remote

import com.sbrosteanu.weatherapp.data.source.remote.locationModel.LocationResponse
import com.sbrosteanu.weatherapp.data.source.remote.remoteServices.GeocodingService
import com.sbrosteanu.weatherapp.data.source.remote.remoteServices.WeatherService
import com.sbrosteanu.weatherapp.data.source.remote.weatherModel.WeatherBaseResponse
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
        private val weatherService: WeatherService,
        private val geocodingService: GeocodingService
) {
    fun getWeatherForCity(lat: String, long: String): Single<WeatherBaseResponse> =
            weatherService.getForecast(latitude = lat, longitude = long)


    fun getCityAddressByName(name: String): Single<List<LocationResponse>> =
            geocodingService.requestCityAddressByName(address = name)

}