package com.oleohialli.weatherapp.utils

sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>()
    class Loading<T>(data: T? = null) : Resource<T>()
    class Error<T>(throwable: Throwable, data:T? = null) : Resource<T>(data, throwable)
}