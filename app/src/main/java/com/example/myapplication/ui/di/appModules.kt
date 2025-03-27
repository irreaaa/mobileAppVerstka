package com.example.myapplication.ui.di

import com.example.myapplication.ui.data.AuthRepository
import com.example.myapplication.ui.data.domain.usecase.AuthUseCase
import com.example.myapplication.ui.data.local.LocalStorage
import com.example.myapplication.ui.data.remote.Auth
import com.example.myapplication.ui.data.remote.RetrofitClient
import com.example.myapplication.ui.screen.SignUp.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single<LocalStorage> { LocalStorage(androidContext()) }
    single<Auth> { RetrofitClient.retrofit }
    single<AuthRepository> { AuthRepository(get()) }
    single<AuthUseCase> { AuthUseCase(get(), get()) }
    viewModel<SignUpViewModel> { SignUpViewModel(get())}
}
