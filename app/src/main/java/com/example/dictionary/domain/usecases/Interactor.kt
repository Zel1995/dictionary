package com.example.dictionary.domain.usecases

import io.reactivex.Observable


interface Interactor<T> {
    fun getData(word:String,fromRemoteSource:Boolean = true): Observable<T>
}