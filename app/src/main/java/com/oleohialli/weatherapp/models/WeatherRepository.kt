package com.oleohialli.weatherapp.models

import androidx.room.withTransaction
import com.oleohialli.weatherapp.Constants
import com.oleohialli.weatherapp.api.WeatherApi
import com.oleohialli.weatherapp.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDb: WeatherDatabase
) {
    private val weatherDao = weatherDb.weatherDao()

    fun getWeather(city: String) = networkBoundResource(
        query = {
            weatherDao.getAllWeather()
        },
        fetch = {
            weatherApi.retrieveWeather(city, Constants.APPID)
        },
        saveFetchResult = {
            weatherDb.withTransaction {
                weatherDao.deleteAllWeather()
                weatherDao.insertAllWeather(it.weatherList)
            }
        }
    )
}