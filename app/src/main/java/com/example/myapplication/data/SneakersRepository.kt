package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.network.Sneackers
import com.example.myapplication.data.remote.network.response.NetworkResponse
import com.example.myapplication.data.remote.network.response.NetworkResponseSneakers
import com.example.myapplication.data.remote.network.response.SneakersResponse

class SneakersRepository(private val sneakersApi: Sneackers) {

    suspend fun getAllSneakers(): NetworkResponseSneakers<List<SneakersResponse>> {
        return try {
            val result = sneakersApi.getAllSneakers()
            NetworkResponseSneakers.Success(result)
        } catch (e: Exception) {
            NetworkResponseSneakers.Error(e.message ?: "Unknown Error")
        }
    }

    suspend fun getPopularSneakers(): NetworkResponseSneakers<List<SneakersResponse>> {
        return try {
            val result = sneakersApi.getPopularSneakers()
            NetworkResponseSneakers.Success(result)
        } catch (e: Exception) {
            NetworkResponseSneakers.Error(e.message ?: "Unknown Error")
        }
    }

    suspend fun getFavorites(): NetworkResponseSneakers<List<SneakersResponse>> {
        return try {
            val result = sneakersApi.getFavorites()
            NetworkResponseSneakers.Success(result)
        } catch (e: Exception) {
            NetworkResponseSneakers.Error(e.message ?: "Unknown Error")
        }
    }

    suspend fun toggleFavorite(sneakerId: Int, isFavorite: Boolean): NetworkResponse<Unit> {
        return try {
            if (isFavorite) {
                sneakersApi.addToFavorites(sneakerId)
            } else {
                sneakersApi.removeFromFavorites(sneakerId)
            }
            NetworkResponse.Success(Unit)
        } catch (e: Exception) {
            NetworkResponse.Error(e.message ?: "Failed to toggle favorite")
        }
    }

    suspend fun getSneakersByCategory(category: String): NetworkResponseSneakers<List<SneakersResponse>> {
        return try {
            val result = sneakersApi.getSneakersByCategory(category)
            NetworkResponseSneakers.Success(result)
        } catch (e: Exception) {
            NetworkResponseSneakers.Error(e.message ?: "Unknown Error")
        }
    }
}
