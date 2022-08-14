package com.example.registration_with_retrofit.model.network

import com.example.registration_with_retrofit.model.LoginResponse
import com.example.registration_with_retrofit.model.RegisterResponse
import com.example.registration_with_retrofit.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("login")
    suspend fun logUser(@Body user: User): Response<LoginResponse>

    @POST("register")
    suspend fun registerUser(@Body user: User): Response<RegisterResponse>

}