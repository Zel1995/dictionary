package com.example.dictionary.data

sealed class AppState<T> {

    class Success<T>(val value:T): AppState<T>()
    class Error<T>(val error:Throwable):AppState<T>()
    class Loading<T>(val loading:Boolean):AppState<T>()
}