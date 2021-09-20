package com.oleohialli.weatherapp.views

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.oleohialli.weatherapp.api.WeatherApi
import com.oleohialli.weatherapp.models.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    repository: WeatherRepository,
    state: SavedStateHandle,
    api: WeatherApi,
) : ViewModel() {

    val searchQuery = state.getLiveData("searchQuery", "")

    //val weather = repository.getWeather("Lagos").asLiveData()

    //Todo: if search query is null, use default city based on location

    private val searchResult = searchQuery.switchMap {
        repository.getWeather(it).asLiveData()
    }


    val weather = searchResult

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