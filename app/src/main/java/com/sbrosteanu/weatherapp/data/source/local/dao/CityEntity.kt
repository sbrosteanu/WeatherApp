package com.sbrosteanu.weatherapp.data.source.local.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        var cityName: String
)