package com.oleohialli.weatherapp.models

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.oleohialli.weatherapp.utils.Converters

@Database(entities = [Weather::class], version = 1)
@TypeConverters(Converters::class)
abstract class WeatherDatabase : RoomDatabase(){

    abstract fun weatherDao(): WeatherDao
}