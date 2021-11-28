package com.example.dictionary.data.network

import com.example.dictionary.data.network.model.DataModelResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    fun search(@Query("search") searchWord: String):Observable<List<DataModelResponse>>
}