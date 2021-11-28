package com.example.dictionary.data.network

import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor : Interceptor {
    companion object{
        val interceptor: BaseInterceptor
            get() = BaseInterceptor()
    }
    private var responseCode = 0

    override fun intercept(chain: Interceptor.Chain): Response {
        val response =chain.proceed(chain.request())
        responseCode = response.code
        return response
    }

}