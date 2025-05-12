package com.example.myapplication.ui.data.remote

sealed class NetworkResponseSneakers <out T>{
    data class Success<T>(val data: T): NetworkResponseSneakers<T>()
    data object Loading: NetworkResponseSneakers<Nothing>()
    data class Error(val errorMessage: String): NetworkResponseSneakers<Nothing>()
}