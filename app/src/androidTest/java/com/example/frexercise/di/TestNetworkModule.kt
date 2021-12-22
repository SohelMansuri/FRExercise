package com.example.frexercise.di

import com.example.frexercise.di.modules.NetworkModule
import com.example.frexercise.networking.HiringService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.mockito.Mockito
import retrofit2.mock.Calls

/**
 * TestNetworkModule designed to replace NetworkModule
 * in order to mock server response easily and test behavior
 * for UI tests.
 */
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
object TestNetworkModule {
    @Provides
    fun provideHiringService(): HiringService {
        val hiringService = Mockito.mock(HiringService::class.java)

        Mockito.`when`(hiringService.getHiringData())
            .thenReturn(
                Calls.failure(Exception())
            )

        return hiringService
    }
}