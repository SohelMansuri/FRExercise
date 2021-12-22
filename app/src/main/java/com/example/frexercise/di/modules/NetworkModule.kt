package com.example.frexercise.di.modules

import com.example.frexercise.networking.Client
import com.example.frexercise.networking.HiringService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * NetworkModule with singleton scope (application lifecycle)
 * put in place to be able to easily swap for testing and more
 * importantly, allow for Inversion of Control.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHiringService(): HiringService {
        return Client.api
    }
}