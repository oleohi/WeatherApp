package com.oleohialli.weatherapp.models

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWeather(weathers: List<Weather>)

    @Query("SELECT * FROM WEATHER_DATA")
    fun getAllWeather(): Flow<List<Weather>>

    @Query("DELETE FROM weather_data")
    suspend fun deleteAllWeather()
}