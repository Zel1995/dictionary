package com.example.dictionary.presentation.main.contract

interface Presenter<V:MvpView> {
    fun getData(word:String,isOnline:Boolean)
}