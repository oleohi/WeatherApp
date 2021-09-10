package com.oleohialli.weatherapp.api

import com.google.gson.annotations.SerializedName
import com.oleohialli.weatherapp.models.Weather

data class WeatherResponse(
    @SerializedName("cod")
    val code: String,
    @SerializedName("message")
    val message: Int,
    @SerializedName("cnt")
    val count: Int,
    @SerializedName("list")
    val weatherList: List<Weather>
)