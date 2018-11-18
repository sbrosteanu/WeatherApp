package com.sbrosteanu.weatherapp.data.source.remote.weatherModel

data class Hourly(
        val summary: String,
        val icon: String,
        val data: List<HourlyWeatherData>
)

data class HourlyWeatherData(
        val time: Int,
        val summary: String,
        val icon: String,
        val precipIntensity: Double,
        val precipProbability: Double,
        val temperature: Double,
        val apparentTemperature: Double,
        val dewPoint: Double,
        val humidity: Double,
        val pressure: Double,
        val windSpeed: Double,
        val windGust: Double,
        val windBearing: Int,
        val cloudCover: Double,
        val uvIndex: Int,
        val visibility: Double,
        val ozone: Double
)