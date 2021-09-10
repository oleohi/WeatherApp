package com.oleohialli.weatherapp.models

import android.util.Log
import androidx.room.withTransaction
import com.oleohialli.weatherapp.Constants
import com.oleohialli.weatherapp.api.WeatherApi
import com.oleohialli.weatherapp.utils.networkBoundResource
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDb: WeatherDatabase
) {
    private val weatherDao = weatherDb.weatherDao()

    fun getWeather() = networkBoundResource(
        query = {
            weatherDao.getAllWeather()
        },
        fetch = {
            weatherApi.retrieveWeather("Lagos", Constants.APPID)
        },
        saveFetchResult = {
            weatherDb.withTransaction {
                weatherDao.deleteAllWeather()
                weatherDao.insertAllWeather(it.weatherList)
            }
        }
    )
}