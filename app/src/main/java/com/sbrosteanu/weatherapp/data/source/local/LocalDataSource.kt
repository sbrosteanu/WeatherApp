package com.sbrosteanu.weatherapp.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sbrosteanu.weatherapp.data.source.local.dao.CityDao
import com.sbrosteanu.weatherapp.data.source.local.dao.CityEntity

@Database(entities = arrayOf(CityEntity::class), version = 1)
abstract class LocalDataSource: RoomDatabase() {

    abstract fun getCityDao(): CityDao

    companion object {
        @Volatile private var INSTANCE: LocalDataSource? = null
        fun getInstance(context: Context): LocalDataSource =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?:  Room.databaseBuilder(context.applicationContext,
                            LocalDataSource::class.java, "weather.db")
                            .build().also {
                                INSTANCE = it
                            }
                }
    }
}