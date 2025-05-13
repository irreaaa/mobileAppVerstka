package com.example.myapplication.ui.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse (
    val token: String,
    val userId: Int,
    val userName: String,
    val email: String
)