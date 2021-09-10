package com.oleohialli.weatherapp.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oleohialli.weatherapp.models.Weather.MainDetails
import com.oleohialli.weatherapp.models.Weather.WeatherData
import dagger.internal.codegen.javapoet.TypeNames.listOf
import okhttp3.internal.toImmutableList
import org.json.JSONStringer
import java.util.Collections.emptyList

@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromMainDetailsToJsonString(main: MainDetails): String {
        val gson = Gson()
         return gson.toJson(main)
    }

    @TypeConverter
    fun fromJsonStringToMainDetails(jsonString: String): MainDetails {
        val gson = Gson()
        return gson.fromJson(jsonString, MainDetails::class.java)
    }

    @TypeConverter
    fun fromWeatherDataList(list: List<WeatherData>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toWeatherList(values: String): List<WeatherData> {
        val listType = object : TypeToken<List<WeatherData?>?>() {}.type
        return Gson().fromJson(values, listType)
    }
}