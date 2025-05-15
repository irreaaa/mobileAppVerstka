package com.example.myapplication.di

import com.example.myapplication.data.AuthRepository
import com.example.myapplication.data.local.DataStoreOnBoarding
import com.example.myapplication.data.local.LocalStorage
import com.example.myapplication.data.remote.network.Auth
import com.example.myapplication.data.remote.AuthInterceptor
import com.example.myapplication.data.remote.RetrofitClient
import com.example.myapplication.data.remote.network.Sneackers
import com.example.myapplication.data.repository.SneakersRepository
import com.example.myapplication.domain.usecase.*
import com.example.myapplication.ui.screen.Popular.PopularViewModel
import com.example.myapplication.ui.screen.SignIn.SignInViewModel
import com.example.myapplication.ui.screen.SignUp.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModules = module {

    single { LocalStorage(androidContext()) }
    single { DataStoreOnBoarding(androidContext()) }

    single { AuthInterceptor(get()) }
    single { RetrofitClient(get()) }

    single<Auth> { get<RetrofitClient>().auth }
    single<Sneackers> { get<RetrofitClient>().sneakers }

    single<AuthRepository> { AuthRepository(get()) }
    single<SneakersRepository> { SneakersRepository(get()) }

    single { AuthUseCase(get(), get()) }
    single { SneakersUseCase(get()) }
    single { FavoriteUseCase(get()) }
    single { TokenUseCase(get()) }
    single { OnBoardingUseCase(get()) }

    viewModelOf(::SignUpViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::PopularViewModel)
}
