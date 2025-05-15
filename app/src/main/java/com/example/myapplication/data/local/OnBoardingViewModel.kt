package com.example.myapplication.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.usecase.OnBoardingUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val onBoardingUseCase: OnBoardingUseCase
) : ViewModel() {

    val isOnBoardingCompleted = onBoardingUseCase.isOnBoardingCompleted
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    fun completeOnBoarding() {
        viewModelScope.launch {
            onBoardingUseCase.completeOnBoarding()
        }
    }
}
