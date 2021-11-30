package com.example.dictionary.di

import android.app.Application

class App:Application() {
    val appComponent = DaggerAppComponent.builder().build()
}