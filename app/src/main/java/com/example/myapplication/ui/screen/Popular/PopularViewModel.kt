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

    fun fetchFavorites() {
        viewModelScope.launch {
            _favoritesState.value = authUseCase.getFavorites()
        }
    }

    fun fetchSneakersByCategory(category: String) {
        viewModelScope.launch {
            _sneakersState.value = NetworkResponseSneakers.Loading
            _sneakersState.value = authUseCase.getSneakersByCategory(category)
        }
    }

    fun fetchSneakers() {
        viewModelScope.launch {
            _sneakersState.value = NetworkResponseSneakers.Loading
            try {
                when(val result = authUseCase.getPopularSneakers()) {
                    is NetworkResponseSneakers.Success -> {
                        Log.d("DATA", "Type: ${result.data?.javaClass?.name}")
                        Log.d("DATA", "Items count: ${(result.data as? List<*>)?.size}")
                        Log.d("DATA", "Received items: ${result.data}")
                        _sneakersState.value = result
                    }
                    is NetworkResponseSneakers.Error -> {
                        Log.e("ERROR", result.errorMessage)
                    }
                    NetworkResponseSneakers.Loading -> {}
                }
            } catch (e: Exception) {
                Log.e("EXCEPTION", "Error: ${e.stackTraceToString()}")
            }
        }
    }

    fun toggleFavorite(sneakerId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            when (val result = authUseCase.toggleFavorite(sneakerId, isFavorite)) {
                is NetworkResponse.Success -> {
                    fetchFavorites()
                    fetchSneakers()
                }
                is NetworkResponse.Error -> {
                    Log.e("FAVORITE", "Ошибка: ${result.errorMessage}")
                }
                NetworkResponse.Loading -> {

                }
            }
        }
    }
}