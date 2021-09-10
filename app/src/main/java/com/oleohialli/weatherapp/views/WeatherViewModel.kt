package com.oleohialli.weatherapp.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.oleohialli.weatherapp.api.WeatherApi
import com.oleohialli.weatherapp.models.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    repository: WeatherRepository,
    api: WeatherApi
) : ViewModel() {

    val weather = repository.getWeather().asLiveData()

//    private val resLiveData = MutableLiveData<List<Weather>>()
//    val weather: LiveData<List<Weather>> = resLiveData
//
//    init {
//        viewModelScope.launch {
//            val weather = api.retrieveWeather("Lagos", Constants.APPID)
//            resLiveData.value = weather.weatherList
//        }
//    }

}