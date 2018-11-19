package com.sbrosteanu.weatherapp.di.modules

import com.sbrosteanu.weatherapp.data.repositories.Repository
import com.sbrosteanu.weatherapp.data.repositories.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl) : Repository
}