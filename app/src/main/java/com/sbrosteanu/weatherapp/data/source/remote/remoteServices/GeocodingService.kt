package com.sbrosteanu.weatherapp.data.source.remote.remoteServices

import com.sbrosteanu.weatherapp.BuildConfig
import com.sbrosteanu.weatherapp.data.source.remote.locationModel.LocationResponse
import com.sbrosteanu.weatherapp.data.source.remote.weatherModel.WeatherBaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GeocodingService {

    @GET("{cityName}?format=json&addressdetails=1&limit=1&polygon_svg=1")
    fun requestCityAddressByName(
            @Path("cityName") address: String
    ): Single<List<LocationResponse>>

}