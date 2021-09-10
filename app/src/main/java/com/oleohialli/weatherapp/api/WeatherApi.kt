package com.oleohialli.weatherapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

    @GET("forecast")
    suspend fun retrieveWeather(
        @Query("q")
        city: String,
        @Query("appid")
        appId: String
    ) : WeatherResponse
}