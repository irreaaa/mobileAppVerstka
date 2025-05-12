package com.example.myapplication.ui.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userName: String,
    val email: String,
    val password: String,
)
