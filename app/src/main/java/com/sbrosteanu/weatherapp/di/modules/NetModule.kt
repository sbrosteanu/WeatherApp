package com.minisoft.myattache.di.modules

import com.sbrosteanu.weatherapp.data.source.remote.remoteServices.GeocodingService
import com.sbrosteanu.weatherapp.data.source.remote.remoteServices.WeatherService
import com.sbrosteanu.weatherapp.data.source.remote.utils.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule(private val weatherBaseURL: String, private val geocodingBaseURL: String) {

    @Provides
    @Singleton
    fun provideOKHttpClient(): OkHttpClient {
        val interceptor = LoggingInterceptor()

        return OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
//                .addInterceptor(interceptor)
                .build()
    }


    @Provides
    @Singleton
    @Named("WEATHER_SERVICE")
    fun provideRetrofitForWeatherService(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(weatherBaseURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    @Named("GEOCODING_SERVICE")
    fun provideRetrofitForGeocodingService(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(geocodingBaseURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }



    @Provides
    @Singleton
    fun provideWeatherService(@Named("WEATHER_SERVICE") retrofit: Retrofit) : WeatherService = retrofit.create(WeatherService::class.java)

    @Provides
    @Singleton
    fun provideGeocodingService(@Named("GEOCODING_SERVICE") retrofit: Retrofit) : GeocodingService = retrofit.create(GeocodingService::class.java)
}