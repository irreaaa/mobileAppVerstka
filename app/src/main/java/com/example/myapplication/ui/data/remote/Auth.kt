package com.example.myapplication.ui.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface Auth {
    @POST("/registration")
    suspend fun registration(@Body user: User)
}