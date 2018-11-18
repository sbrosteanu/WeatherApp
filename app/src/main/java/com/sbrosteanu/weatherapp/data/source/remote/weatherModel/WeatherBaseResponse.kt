package com.sbrosteanu.weatherapp.data.source.remote.weatherModel

import com.google.gson.annotations.SerializedName


class WeatherBaseResponse(
        val latitude: Double,
        val longitude: Double,
        val timezone: String,
        val currently: CurrentWeatherData,
        val minutely: Minutely,
        val hourly: Hourly,
        val daily: Daily,
        val alerts: List<Alert>,
        val flags: Flags,
        val offset: Int //-5

)

class CurrentWeatherData(
        val time: Int,
        val summary: String,
        val icon: String,
        val nearestStormDistance: Int,
        val nearestStormBearing: Int,
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

data class Flags(
        val sources: List<String>,
        @SerializedName("isd-stations")
        val isdStations: List<String>,
        val units: String,
        val id: Long
)

data class Alert(
        val title: String,
        val regions: List<String>,
        val severity: String,
        val time: Int,
        val expires: Int,
        val description: String,
        val uri: String
)

