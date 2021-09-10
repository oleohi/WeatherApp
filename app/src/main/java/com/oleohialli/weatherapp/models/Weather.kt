package com.oleohialli.weatherapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather_data")
data class Weather(
    @SerializedName("dt")
    @PrimaryKey val date: Long,

    @SerializedName("main")
    val mainTemp: MainDetails,

    @SerializedName("weather")
    val weather: List<WeatherData>,

    @SerializedName("dt_txt")
    val dateText: String
) {
    data class MainDetails(
        val temp: Double,
        val temp_min: Double,
        val temp_max: Double,
        val humidity: Double
    )

    data class WeatherData(
        val main: String,
        val description: String
    )
}
