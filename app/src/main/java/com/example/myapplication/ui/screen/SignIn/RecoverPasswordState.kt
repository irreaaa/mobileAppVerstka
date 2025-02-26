package com.example.myapplication.ui.screen.SignIn

data class RecoverPasswordState (
    var email: String = "",
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
)