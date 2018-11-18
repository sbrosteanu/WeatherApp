package com.sbrosteanu.weatherapp.data.source.remote.locationModel



data class LocationResponse(
        val place_id: String,
        val lat: String,
        val lon: String,
        val display_name: String
)
