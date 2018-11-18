package com.sbrosteanu.weatherapp.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding2.widget.RxTextView
import com.sbrosteanu.weatherapp.Application
import com.sbrosteanu.weatherapp.R
import com.sbrosteanu.weatherapp.data.source.local.dao.CityEntity
import com.sbrosteanu.weatherapp.domain.dto.WeatherDetailsDTO
import com.sbrosteanu.weatherapp.ui.base.BaseActivity
import com.sbrosteanu.weatherapp.ui.viewModels.WeatherViewModel
import com.sbrosteanu.weatherapp.ui.viewModels.factory.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var vmFactory: ViewModelFactory
    private lateinit var viewModel: WeatherViewModel

    private var searchedCityNames = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Application.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, vmFactory).get(WeatherViewModel::class.java)

        val inputTextObservable = RxTextView.textChanges(tvCityName)
                .map { inputText -> inputText.isEmpty() }
                .distinctUntilChanged()

        compositeDisposable.add(setupTextObserver(inputTextObservable))

        setupSearchClickListener()
    }

    override fun onStart() {
        super.onStart()
        compositeDisposable.add(setupSearchedCitiesObserver())
    }

    private fun setupSearchClickListener() {
        btnSearch.setOnClickListener {
            toggleUIState(hasRequestEnded = false)
            val searchedCityName = tvCityName.text.toString()
            setupWeatherDetailsObserver(searchedCityName)?.let { it -> compositeDisposable.add(it) }
        }
    }

    private fun setupWeatherDetailsObserver(searchedCityName: String): Disposable? {
        return viewModel.getWeather(searchedCityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { weatherResponse: WeatherDetailsDTO? ->
                            toggleUIState(hasRequestEnded = true)
                            showDetails(weatherResponse)
                            if (!(searchedCityNames.contains(searchedCityName)))
                                viewModel.addCity(searchedCityName)
                        },
                        { throwable: Throwable? ->
                            toggleUIState(hasRequestEnded = true)
                            throwable?.let {
                                Timber.e(it)
                            }
                            Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                        }
                )
    }



    private fun setupTextObserver(inputTextObservable: Observable<Boolean>) : Disposable {
        return inputTextObservable.subscribe { isEmpty: Boolean ->
            cityTextInputLayout.error = getString(R.string.invalid_city_name)
            cityTextInputLayout.isErrorEnabled = true
            btnSearch?.isEnabled = !isEmpty
        }
    }


    private fun setupSearchedCitiesObserver(): Disposable {
        return viewModel.getCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { citiesList: List<CityEntity> ->
                    searchedCityNames.clear()
                    citiesList.forEach { searchedCityNames.add(it.cityName) }
                    val adapter = ArrayAdapter(this,
                            android.R.layout.simple_dropdown_item_1line, searchedCityNames)
                    tvCityName.setAdapter(adapter)
                    tvCityName.threshold = 0
                }
    }

    private fun toggleUIState(hasRequestEnded: Boolean) {
        if (hasRequestEnded) {
            btnSearch.isEnabled = false
            btnSearch.alpha = 0.5F
            cityTextInputLayout.isEnabled = false
            cityTextInputLayout.alpha = 0.5F
            progressBar.visibility = View.VISIBLE
        } else {
            btnSearch.isEnabled = true
            btnSearch.alpha = 1.0F
            cityTextInputLayout.isEnabled = true
            cityTextInputLayout.alpha = 1.0F
            progressBar.visibility = View.VISIBLE
        }

    }


}
