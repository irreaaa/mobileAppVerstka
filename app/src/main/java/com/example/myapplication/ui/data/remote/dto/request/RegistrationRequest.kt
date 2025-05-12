package com.example.myapplication.ui.data.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest (
    val userName: String,
    val email: String,
    val password: String
)