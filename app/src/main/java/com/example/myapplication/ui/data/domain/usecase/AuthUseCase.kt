package com.example.myapplication.ui.data.domain.usecase

import androidx.collection.emptyIntSet
import com.example.myapplication.ui.data.AuthRepository
import com.example.myapplication.ui.data.local.LocalStorage
import com.example.myapplication.ui.data.remote.NetworkResponse
import com.example.myapplication.ui.data.remote.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class AuthUseCase(private val localStorage: LocalStorage,
                  private val authRepository: AuthRepository) {
    val token: Flow<String> by lazy{
        localStorage.tokenFlow
    }
    suspend fun signUp(user: User): Flow<NetworkResponse> = flow {
            try  {
                emit(NetworkResponse.Loading)
                val result = authRepository.signUp(user)
                localStorage.setToken(result.second)
                emit(NetworkResponse.Success(result))
            }
            catch (e: Exception){
                e.message?.let {
                    emit(NetworkResponse.Error(it))
                    return@flow
                }
                emit(NetworkResponse.Error("Unknown Error"))
            }
        }
    }
