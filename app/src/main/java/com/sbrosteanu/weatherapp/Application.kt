package com.sbrosteanu.weatherapp

import android.app.Application
import com.minisoft.myattache.di.modules.NetModule
import com.sbrosteanu.weatherapp.di.component.AppComponent
import com.sbrosteanu.weatherapp.di.component.DaggerAppComponent
import com.sbrosteanu.weatherapp.di.modules.AppModule
import com.sbrosteanu.weatherapp.di.modules.DatabaseModule
import timber.log.Timber

class Application : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(app = this))
                .netModule(NetModule(BuildConfig.URL, BuildConfig.GEOCODE_URL))
                .databaseModule(DatabaseModule())
                .build()
    }




}