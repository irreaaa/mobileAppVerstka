package com.example.myapplication.domain.usecase

import com.example.myapplication.data.local.LocalStorage
import kotlinx.coroutines.flow.Flow

class TokenUseCase(private val localStorage: LocalStorage) {

    val token: Flow<String> = localStorage.tokenFlow

    suspend fun saveToken(token: String) {
        localStorage.setToken(token)
    }

    suspend fun clearToken() {
        localStorage.clearToken()
    }
}
