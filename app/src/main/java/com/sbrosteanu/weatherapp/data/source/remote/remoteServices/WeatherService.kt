package com.sbrosteanu.weatherapp.data.source.remote.remoteServices

import com.sbrosteanu.weatherapp.BuildConfig
import com.sbrosteanu.weatherapp.data.source.remote.weatherModel.WeatherBaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("${BuildConfig.API_KEY}/{latitude},{longitude}")
    fun getForecast(
            @Path(value = "latitude") latitude: String,
            @Path(value = "longitude") longitude: String
    ): Single<WeatherBaseResponse>

}