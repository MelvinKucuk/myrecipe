package com.melvin.myrecipe.core.data

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val errorMessage: String) : Resource<T>()
}