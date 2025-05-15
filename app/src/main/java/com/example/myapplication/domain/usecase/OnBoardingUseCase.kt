package com.example.myapplication.domain.usecase

import com.example.myapplication.data.local.DataStoreOnBoarding
import kotlinx.coroutines.flow.Flow

class OnBoardingUseCase(private val dataStoreOnBoarding: DataStoreOnBoarding) {

    val isOnBoardingCompleted: Flow<Boolean>
        get() = dataStoreOnBoarding.onBoardingCompleted

    suspend fun completeOnBoarding() {
        dataStoreOnBoarding.setOnBoardingCompleted(true)
    }
}
