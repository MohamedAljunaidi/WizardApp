package com.assignment.di

import com.assignment.network.common.NetworkConstants
import com.assignment.services.ApiRequests
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkFactory {

    fun create(
        retrofit:Retrofit
    ): ApiRequests {
        return retrofit.create(ApiRequests::class.java)
    }



     fun getRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


     fun getOkHttpClient(
         httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(NetworkConstants.TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstants.TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NetworkConstants.TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)

        return okHttpClient.build()
    }
}