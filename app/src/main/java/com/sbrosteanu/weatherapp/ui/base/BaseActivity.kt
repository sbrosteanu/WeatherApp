package com.sbrosteanu.weatherapp.ui.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.sbrosteanu.weatherapp.R
import com.sbrosteanu.weatherapp.domain.dto.WeatherDetailsDTO
import com.sbrosteanu.weatherapp.ui.DetailsActivity
import io.reactivex.disposables.CompositeDisposable
import org.parceler.Parcels

open class BaseActivity : AppCompatActivity() {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    fun showDetails(weatherResponse: WeatherDetailsDTO?) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(getString(R.string.intentWeatherDetailsParcelerBundleName), Parcels.wrap(weatherResponse))
        startActivity(intent)
    }

}