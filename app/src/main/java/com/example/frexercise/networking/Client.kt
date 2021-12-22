package com.example.frexercise.networking

/**
 * Client singleton created to allow for one time creation
 * of HiringService api for retrofit purposes.
 */
object Client {
    val api: HiringService = ConfigSingleton.getRetrofitService().create(HiringService::class.java)
}