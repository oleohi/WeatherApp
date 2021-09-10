package com.oleohialli.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.oleohialli.weatherapp.BuildConfig
import com.oleohialli.weatherapp.api.WeatherApi
import com.oleohialli.weatherapp.models.WeatherDao
import com.oleohialli.weatherapp.models.WeatherDatabase
import com.oleohialli.weatherapp.utils.Converters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        //Building Http Client
        val httpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        // Adding logging capabilities
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            val loggingInterceptor =
                httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                }
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return Retrofit.Builder()
            .baseUrl(WeatherApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClientBuilder.build())
            .build()
    }


    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi =
        retrofit.create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): WeatherDatabase =
        Room.databaseBuilder(app, WeatherDatabase::class.java, "weather_database")
            .addTypeConverter(Converters())
            .build()
}