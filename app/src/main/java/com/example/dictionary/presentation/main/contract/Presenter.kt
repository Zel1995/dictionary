package com.example.dictionary.presentation.main.contract

interface Presenter<V:MvpView> {

    fun attachView(view:V)
    fun detachView(view:V)
    fun getData(word:String,isOnline:Boolean)
}