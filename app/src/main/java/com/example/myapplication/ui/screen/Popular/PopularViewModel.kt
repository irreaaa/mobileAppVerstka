package com.example.myapplication.ui.screen.Popular

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.data.domain.usecase.AuthUseCase
import com.example.myapplication.ui.data.remote.NetworkResponse
import com.example.myapplication.ui.data.remote.NetworkResponseSneakers
import com.example.myapplication.ui.data.remote.dto.response.SneakersResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PopularViewModel(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _sneakersState = MutableStateFlow<NetworkResponseSneakers<List<SneakersResponse>>>(NetworkResponseSneakers.Loading)
    val sneakersState: StateFlow<NetworkResponseSneakers<List<SneakersResponse>>> = _sneakersState

    private val _favoritesState = MutableStateFlow<NetworkResponseSneakers<List<SneakersResponse>>>(NetworkResponseSneakers.Loading)
    val favoritesState: StateFlow<NetworkResponseSneakers<List<SneakersResponse>>> = _favoritesState

    private var currentCategory: String = "Все"

    fun fetchFavorites() {
        viewModelScope.launch {
            val result = authUseCase.getFavorites()
            if (result is NetworkResponseSneakers.Success) {
                val modified = result.data.map { it.copy(isFavorite = true) }
                _favoritesState.value = NetworkResponseSneakers.Success(modified)
            } else {
                _favoritesState.value = result
            }
        }
    }


    fun fetchSneakersByCategory(category: String) {
        currentCategory = category
        viewModelScope.launch {
            _sneakersState.value = NetworkResponseSneakers.Loading
            _sneakersState.value = mergeSneakersWithFavorites(category)
        }
    }

    fun toggleFavorite(sneakerId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            when (val result = authUseCase.toggleFavorite(sneakerId, isFavorite)) {
                is NetworkResponse.Success -> {
                    fetchFavorites()
                    fetchSneakersByCategory(currentCategory)
                }
                is NetworkResponse.Error -> {
                    Log.e("FAVORITE", "Ошибка: ${result.errorMessage}")
                }
                NetworkResponse.Loading -> {}
            }
        }
    }

    private suspend fun mergeSneakersWithFavorites(category: String): NetworkResponseSneakers<List<SneakersResponse>> {
        val allSneakersResult = authUseCase.getAllSneakers()
        val favoritesResult = authUseCase.getFavorites()

        if (allSneakersResult is NetworkResponseSneakers.Success &&
            favoritesResult is NetworkResponseSneakers.Success) {

            val allSneakers = allSneakersResult.data
            val favoriteIds = favoritesResult.data.map { it.id }.toSet()

            val merged = allSneakers.map {
                it.copy(isFavorite = it.id in favoriteIds)
            }

            val filtered = when (category) {
                "Все" -> merged
                "Популярное" -> merged.filter { it.isPopular }
                else -> merged.filter { it.category.equals(category, ignoreCase = true) }
            }

            return NetworkResponseSneakers.Success(filtered)
        }

        return NetworkResponseSneakers.Error("Ошибка при получении данных с сервера")
    }
}