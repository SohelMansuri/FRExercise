package com.example.frexercise.networking

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * ConfigSingleton which does a one-time setup of Retrofit
 * with a sample OkHttpClient and lenient GsonBuilder
 * to allow for client-server communication with the BASE_URL.
 */
object ConfigSingleton {
    private var retrofit: Retrofit? = null
    private const val BASE_URL: String = "https://fetch-hiring.s3.amazonaws.com/"

    fun getRetrofitService(): Retrofit {
        if(retrofit == null) {
            val client = OkHttpClient.Builder()
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

            val gson = GsonBuilder().setLenient().create()

            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create(gson)
                )
                .build()
        }

        return retrofit!!
    }
}