package com.sbrosteanu.weatherapp.data.source.remote.weatherModel

data class Daily(
        val summary: String,
        val icon: String,
        val data: List<DailyWeatherData>
)

data class DailyWeatherData(
        val time: Int,
        val summary: String,
        val icon: String,
        val sunriseTime: Int,
        val sunsetTime: Int,
        val moonPhase: Double,
        val precipIntensity: Double,
        val precipIntensityMax: Double,
        val precipIntensityMaxTime: Int,
        val precipProbability: Double,
        val precipType: String,
        val temperatureHigh: Double,
        val temperatureHighTime: Int,
        val temperatureLow: Double,
        val temperatureLowTime: Int,
        val apparentTemperatureHigh: Double,
        val apparentTemperatureHighTime: Int,
        val apparentTemperatureLow: Double,
        val apparentTemperatureLowTime: Int,
        val dewPoint: Double,
        val humidity: Double,
        val pressure: Double,
        val windSpeed: Double,
        val windGust: Double,
        val windGustTime: Int,
        val windBearing: Int,
        val cloudCover: Double,
        val uvIndex: Int,
        val uvIndexTime: Int,
        val visibility: Double,
        val ozone: Double,
        val temperatureMin: Double,
        val temperatureMinTime: Int,
        val temperatureMax: Double,
        val temperatureMaxTime: Int,
        val apparentTemperatureMin: Double,
        val apparentTemperatureMinTime: Int,
        val apparentTemperatureMax: Double,
        val apparentTemperatureMaxTime: Int
)