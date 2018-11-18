package com.sbrosteanu.weatherapp.di.component

import com.sbrosteanu.weatherapp.di.modules.AppModule
import com.minisoft.myattache.di.modules.NetModule
import com.sbrosteanu.weatherapp.Application
import com.sbrosteanu.weatherapp.di.modules.DatabaseModule
import com.sbrosteanu.weatherapp.ui.MainActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(AndroidInjectionModule::class, AppModule::class, NetModule::class, DatabaseModule::class)
)
interface AppComponent {
    fun inject(app: Application)
    fun inject(mainActivity: MainActivity)
}