package com.sbrosteanu.weatherapp.data.repositories

import com.sbrosteanu.weatherapp.data.source.local.LocalDataSource
import com.sbrosteanu.weatherapp.data.source.local.dao.CityEntity
import com.sbrosteanu.weatherapp.data.source.remote.RemoteDataSource
import com.sbrosteanu.weatherapp.data.source.remote.locationModel.LocationResponse
import com.sbrosteanu.weatherapp.data.source.remote.weatherModel.WeatherBaseResponse
import com.sbrosteanu.weatherapp.domain.dto.WeatherDetailsDTO
import com.sbrosteanu.weatherapp.utils.DTOTransformer
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class Repository @Inject constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource
) {

    fun getWeather(cityName: String): Single<WeatherDetailsDTO> {

        return remoteDataSource.getCityAddressByName(cityName)
                .flatMap { locationResponseList: List<LocationResponse> ->
                    remoteDataSource.getWeatherForCity(
                            locationResponseList.first().lat,
                            locationResponseList.first().lon
                    )
                            .map { weatherResponse: WeatherBaseResponse ->
                                DTOTransformer.transformToWeatherDetailsDTO(
                                        locationResponseList.first().display_name,
                                        weatherResponse
                                )
                            }
                }
                .retry(1)

    }


    fun getCities(): Flowable<List<CityEntity>> {
        return localDataSource.getCityDao().getAllCities()
    }

    fun addCity(cityName: String) {
        Completable.fromCallable {
            localDataSource.getCityDao().insertCity(CityEntity(cityName = cityName))
        }.subscribeOn(Schedulers.io()).subscribe()
    }

}