package com.example.myapplication.ui.data.remote

import com.example.myapplication.ui.data.remote.dto.request.RegistrationRequest
import com.example.myapplication.ui.data.remote.dto.response.SneakersResponse
import com.example.myapplication.ui.data.remote.dto.response.TokenResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Auth {
    @POST("/registration")
    suspend fun registration(@Body registrationRequest: RegistrationRequest): TokenResponse

    @POST("/login")
    suspend fun authorization(@Body loginRequest: LoginRequest): TokenResponse

    @GET("/allSneakers")
    suspend fun getAllSneakers(): List<SneakersResponse>

    @GET("/sneakers/popular")
    suspend fun getPopularSneakers(): List<SneakersResponse>

    @GET("/sneakers/{category}")
    suspend fun getSneakersByCategory(@Path("category") category: String): List<SneakersResponse>

    @GET("/favorites")
    suspend fun getFavorites(): List<SneakersResponse>

    @POST("/favorites/{sneakerId}")
    suspend fun addToFavorites(@Path("sneakerId") sneakerId: Int): Response<ResponseBody>

    @DELETE("/favorites/{sneakerId}")
    suspend fun removeFromFavorites(@Path("sneakerId") sneakerId: Int): Response<ResponseBody>
}