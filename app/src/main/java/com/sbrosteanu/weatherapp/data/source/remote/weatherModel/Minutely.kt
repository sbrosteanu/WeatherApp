package com.sbrosteanu.weatherapp.data.source.remote.weatherModel

data class Minutely(
        val summary: String,
        val icon: String,
        val data: List<Data>
)

data class Data(
        val time: Int,
        val precipIntensity: Double,
        val precipProbability: Double
)