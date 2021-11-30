package com.example.dictionary.data.network

import com.example.dictionary.data.network.model.DataModelResponse
import com.example.dictionary.domain.Model.DataModel
import com.example.dictionary.domain.datasource.DataSource
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImplementation {
    companion object {
        private const val BASE_URL_LOCATIONS = "https://dictionary.skyeng.ru/api/public/v1/"
    }

    fun getData(word:String): Observable<List<DataModelResponse>> = getService().search(word)

    private fun getService(): ApiService = createRetrofit().create(ApiService::class.java)

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createOkHttpClient(BaseInterceptor.interceptor))
            .build()
    }

    private fun createOkHttpClient(interceptor: BaseInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    }
}
