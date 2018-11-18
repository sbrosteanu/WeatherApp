package com.sbrosteanu.weatherapp.di.modules

import com.sbrosteanu.weatherapp.Application
import com.sbrosteanu.weatherapp.data.source.local.LocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(app: Application) =
            LocalDataSource.getInstance(app)
}