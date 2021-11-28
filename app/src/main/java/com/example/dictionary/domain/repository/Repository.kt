package com.example.dictionary.domain.repository

import io.reactivex.Observable

interface Repository<T> {
    fun getData(world:String): Observable<T>
}