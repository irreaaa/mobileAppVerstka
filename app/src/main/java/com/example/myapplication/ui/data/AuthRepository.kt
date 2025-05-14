package com.example.myapplication.ui.data

import com.example.myapplication.ui.data.remote.Auth
import com.example.myapplication.ui.data.remote.LoginRequest
import com.example.myapplication.ui.data.remote.NetworkResponse
import com.example.myapplication.ui.data.remote.NetworkResponseSneakers
import com.example.myapplication.ui.data.remote.User
import com.example.myapplication.ui.data.remote.dto.request.RegistrationRequest
import com.example.myapplication.ui.data.remote.dto.response.SneakersResponse
import com.example.myapplication.ui.data.remote.dto.response.TokenResponse
import kotlinx.coroutines.delay

class AuthRepository(private val api: Auth) {
    suspend fun signUp(registrationRequest: RegistrationRequest): TokenResponse {
        delay(3000)
        return api.registration(registrationRequest)
    }
    suspend fun signIn(loginRequest: LoginRequest): TokenResponse {
        delay(3000)
        return api.authorization(loginRequest)
    }

    suspend fun getAllSneakers(): NetworkResponseSneakers<List<SneakersResponse>> {
        return try {
            val result = api.getAllSneakers()
            NetworkResponseSneakers.Success(result)
        } catch (e: Exception) {
            NetworkResponseSneakers.Error(e.message ?: "Unknown Error")
        }
    }

    suspend fun getPopularSneakers(): NetworkResponseSneakers<List<SneakersResponse>> {
        return try {
            val result = api.getAllSneakers()
            NetworkResponseSneakers.Success(result)
        } catch (e: Exception) {
            NetworkResponseSneakers.Error(e.message ?: "Unknown Error")
        }
    }

    suspend fun getFavorites(): NetworkResponseSneakers<List<SneakersResponse>> {
        return try {
            val result = api.getFavorites()
            NetworkResponseSneakers.Success(result)
        } catch (e: Exception) {
            NetworkResponseSneakers.Error(e.message ?: "Unknown Error")
        }
    }

    suspend fun toggleFavorite(sneakerId: Int, isFavorite: Boolean): NetworkResponse<Unit> {
        return try {
            if (isFavorite) {
                api.addToFavorites(sneakerId)
            } else {
                api.removeFromFavorites(sneakerId)
            }
            NetworkResponse.Success(Unit)
        } catch (e: Exception) {
            NetworkResponse.Error(e.message ?: "Failed to toggle favorite")
        }
    }

    suspend fun getSneakersByCategory(category: String): NetworkResponseSneakers<List<SneakersResponse>> {
        return try {
            val result = api.getSneakersByCategory(category)
            NetworkResponseSneakers.Success(result)
        } catch (e: Exception) {
            NetworkResponseSneakers.Error(e.message ?: "Unknown Error")
        }
    }
}