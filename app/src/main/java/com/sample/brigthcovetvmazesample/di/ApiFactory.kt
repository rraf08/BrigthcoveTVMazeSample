package com.sample.brigthcovetvmazesample.di

import com.sample.brigthcovetvmazesample.util.API_BASE_URL
import com.sample.brigthcovetvmazesample.util.CONNECTION_TIMEOUT
import com.sample.brigthcovetvmazesample.util.READ_TIMEOUT
import com.sample.brigthcovetvmazesample.util.WRITE_TIMEOUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiFactory @Inject constructor() {

    fun <Api> createInstance(api: Class<Api>): Api {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

    private fun getClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY


        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder().also {
                        it.addHeader("X-Requested-With", "XMLHttpRequest")
                            .addHeader("content-type", "application/json")
                    }.build()
                )
            }.also { client ->
                client.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MINUTES)
                    .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            }.build()
    }
}