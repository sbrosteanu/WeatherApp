package com.sbrosteanu.weatherapp

import com.sbrosteanu.weatherapp.data.repositories.Repository
import com.sbrosteanu.weatherapp.data.source.local.LocalDataSource
import com.sbrosteanu.weatherapp.data.source.local.dao.CityDao
import com.sbrosteanu.weatherapp.data.source.remote.RemoteDataSource
import com.sbrosteanu.weatherapp.data.source.remote.remoteServices.GeocodingService
import com.sbrosteanu.weatherapp.data.source.remote.remoteServices.WeatherService
import com.sbrosteanu.weatherapp.domain.dto.HourlyWeatherDTO
import com.sbrosteanu.weatherapp.domain.dto.WeatherDetailsDTO
import com.sbrosteanu.weatherapp.domain.dto.WeeklyWeatherDTO
import com.sbrosteanu.weatherapp.ui.viewModels.WeatherViewModel
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ViewModelTest {


    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var weatherService: WeatherService

    @Mock
    private lateinit var searchCityDao: CityDao

    @Mock
    private lateinit var geocodingService: GeocodingService


    @Captor
    private lateinit var stringCityNameArgumentCaptor: ArgumentCaptor<String>

    private lateinit var weatherViewModel: WeatherViewModel

    private lateinit var repository: Repository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = Repository(remoteDataSource, localDataSource)
        weatherViewModel = WeatherViewModel(repository = repository)

    }

    @Test
    fun testGetWeather_fetchFromRepository() {
        val cityName = "London"
        val weatherDetailsDTO = WeatherDetailsDTO(cityName, "Summary", 23.23, 23.23, 12.42, 15.2, weeklyWeatherDtoArray, hourlyWeatherDtoArray, hourlyFormatedStrings)

        Mockito.`when`(repository.getWeather(cityName)).thenReturn(Single.just(weatherDetailsDTO))

        weatherViewModel.getWeather(cityName)

        Mockito.verify<Repository>(repository).getWeather(stringCityNameArgumentCaptor.capture())

        assertEquals(cityName, stringCityNameArgumentCaptor.value)
    }


    companion object {
        val weeklyWeatherDTO = WeeklyWeatherDTO("34", "12", "Monday", "Rainy")
        val weeklyWeatherDtoArray = ArrayList<WeeklyWeatherDTO>()

        val hourlyWeatherDTO = HourlyWeatherDTO(123123L, 2.2)
        val hourlyWeatherDtoArray = ArrayList<HourlyWeatherDTO>()

        val hourlyFormatedStrings = ArrayList<String>()

    }

}