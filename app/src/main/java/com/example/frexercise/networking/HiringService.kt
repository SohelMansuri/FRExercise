package com.example.frexercise.networking

import com.example.frexercise.models.ListItem
import retrofit2.Call
import retrofit2.http.GET

/**
 * Retrofit Interface for handling hiring data related communication with server.
 */
interface HiringService {
    @GET("hiring.json")
    fun getHiringData(): Call<List<ListItem>>
}