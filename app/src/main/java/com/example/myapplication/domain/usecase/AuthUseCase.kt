package com.example.myapplication.domain.usecase

import com.example.myapplication.data.AuthRepository
import com.example.myapplication.data.local.LocalStorage
import com.example.myapplication.data.remote.network.request.LoginRequest
import com.example.myapplication.data.remote.network.request.RegistrationRequest
import com.example.myapplication.data.remote.network.response.NetworkResponse
import com.example.myapplication.data.remote.network.response.TokenResponse
import com.example.myapplication.domain.EmailValidator
import com.example.myapplication.domain.PasswordValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthUseCase(
    private val localStorage: LocalStorage,
    private val authRepository: AuthRepository
) {
    val token: Flow<String> by lazy {
        localStorage.tokenFlow
    }

    suspend fun signUp(registrationRequest: RegistrationRequest): Flow<NetworkResponse<TokenResponse>> = flow {
        emit(NetworkResponse.Loading)

        if (!EmailValidator.validate(registrationRequest.email)) {
            emit(NetworkResponse.Error("Invalid email format"))
            return@flow
        }

        if (!PasswordValidator.validate(registrationRequest.password)) {
            emit(NetworkResponse.Error("Password must contain: 8+ chars, 1 uppercase, 1 digit, 1 special char"))
            return@flow
        }

        try {
            val result = authRepository.signUp(registrationRequest)
            localStorage.setToken(result.token)
            emit(NetworkResponse.Success(result))
        } catch (e: Exception) {
            emit(NetworkResponse.Error(e.message ?: "Unknown Error"))
        }
    }

    suspend fun signIn(loginRequest: LoginRequest): Flow<NetworkResponse<TokenResponse>> = flow {
        emit(NetworkResponse.Loading)
        try {
            val result = authRepository.signIn(loginRequest)
            localStorage.setToken(result.token)
            emit(NetworkResponse.Success(result))
        } catch (e: Exception) {
            emit(NetworkResponse.Error(e.message ?: "Unknown Error"))
        }
    }
}
