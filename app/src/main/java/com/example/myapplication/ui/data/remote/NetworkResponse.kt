package com.example.myapplication.ui.data.remote

sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data object Loading : NetworkResponse<Nothing>()
    data class Error(val errorMessage: String) : NetworkResponse<Nothing>()
}