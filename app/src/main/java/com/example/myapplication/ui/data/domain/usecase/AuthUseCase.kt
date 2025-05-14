package com.example.myapplication.ui.data.domain.usecase

import com.example.myapplication.ui.data.AuthRepository
import com.example.myapplication.ui.data.domain.EmailValidator
import com.example.myapplication.ui.data.domain.PasswordValidator
import com.example.myapplication.ui.data.local.LocalStorage
import com.example.myapplication.ui.data.remote.LoginRequest
import com.example.myapplication.ui.data.remote.NetworkResponse
import com.example.myapplication.ui.data.remote.NetworkResponseSneakers
import com.example.myapplication.ui.data.remote.User
import com.example.myapplication.ui.data.remote.dto.request.RegistrationRequest
import com.example.myapplication.ui.data.remote.dto.response.SneakersResponse
import com.example.myapplication.ui.data.remote.dto.response.TokenResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthUseCase(private val localStorage: LocalStorage,
                  private val authRepository: AuthRepository) {
    val token: Flow<String> by lazy{
        localStorage.tokenFlow
    }
    suspend fun signUp(user: User): Flow<NetworkResponse<TokenResponse>> = flow {
        try {
            emit(NetworkResponse.Loading)
            if (!EmailValidator.validate(user.email)) {
                emit(NetworkResponse.Error("Invalid email format    "))
                return@flow
            }
            if (!PasswordValidator.validate(user.password)) {
                emit(NetworkResponse.Error("Password must contain: 8+ chars, 1 uppercase, 1 digit, 1 special char"))
                return@flow
            }

            val result = authRepository.signUp(user)
            localStorage.setToken(result.token)
            emit(NetworkResponse.Success(result))
        } catch (e: Exception) {
            emit(NetworkResponse.Error(e.message ?: "Unknown Error"))

            println("Registration failed: ${e.message}")
        }
    }


    suspend fun signIn(loginRequest: LoginRequest): Flow<NetworkResponse<TokenResponse>> = flow {
        try {
            emit(NetworkResponse.Loading)
            val result = authRepository.signIn(loginRequest)
            localStorage.setToken(result.token)
            emit(NetworkResponse.Success(result))
        } catch (e: Exception) {
            emit(NetworkResponse.Error(e.message ?: "Unknown Error"))
        }
    }

    suspend fun getAllSneakers(): NetworkResponseSneakers<List<SneakersResponse>>{
        return authRepository.getAllSneakers()
    }

    suspend fun getSneakersByCategory(category: String): NetworkResponseSneakers<List<SneakersResponse>> {
        val sneakersByCategory = authRepository.getSneakersByCategory(category)
        println(sneakersByCategory);
        return sneakersByCategory;
    }

    suspend fun getPopularSneakers(): NetworkResponseSneakers<List<SneakersResponse>> {
        return authRepository.getPopularSneakers()
    }

    suspend fun getFavorites(): NetworkResponseSneakers<List<SneakersResponse>> {
        return authRepository.getFavorites()
    }

    suspend fun toggleFavorite(sneakerId: Int, isFavorite: Boolean): NetworkResponse<Unit> {
        return authRepository.toggleFavorite(sneakerId, isFavorite)
    }
}