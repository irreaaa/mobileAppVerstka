package com.example.myapplication.ui.screen.SignIn

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {
    var signUpState = mutableStateOf(SignUpState())
        private set

    val emailHasError = derivedStateOf {
        if(signUpState.value.email.isEmpty()) return@derivedStateOf false
        !android.util.Patterns.EMAIL_ADDRESS.matcher(signUpState.value.email).matches()
    }

    fun setName(name: String){
        signUpState.value = signUpState.value.copy(name = name)
    }

    fun setEmail(email: String){
        signUpState.value = signUpState.value.copy(email = email)
    }

    fun setPassword(password: String){
        signUpState.value = signUpState.value.copy(password = password)
    }
}