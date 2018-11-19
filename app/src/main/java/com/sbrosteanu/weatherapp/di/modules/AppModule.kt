package com.sbrosteanu.weatherapp.di.modules

import com.sbrosteanu.weatherapp.Application
import com.sbrosteanu.weatherapp.data.repositories.Repository
import com.sbrosteanu.weatherapp.data.repositories.RepositoryImpl
import com.sbrosteanu.weatherapp.ui.viewModels.factory.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideViewModelFactory(repository: Repository): ViewModelFactory {
        return ViewModelFactory(repository = repository)
    }

}