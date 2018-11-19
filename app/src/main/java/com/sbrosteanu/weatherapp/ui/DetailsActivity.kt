package com.sbrosteanu.weatherapp.ui

import android.os.Bundle
import com.sbrosteanu.weatherapp.R
import com.sbrosteanu.weatherapp.domain.dto.WeatherDetailsDTO
import com.sbrosteanu.weatherapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_details.*
import org.parceler.Parcels

class DetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details)
        val weatherDetails = Parcels.unwrap<WeatherDetailsDTO>(intent.getParcelableExtra(getString(R.string.intentWeatherDetailsParcelerBundleName)))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupWeatherDetails(weatherDetails)

    }

    private fun setupWeatherDetails(weatherDetails: WeatherDetailsDTO) {
        tvCityName.text = weatherDetails.cityName
        tvCurrentConditions.text = weatherDetails.weatherSummary
        tvCurrentHumidity.text = weatherDetails.humidity.toString()
        tvCurrentTemperature.text = weatherDetails.temperature.toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}