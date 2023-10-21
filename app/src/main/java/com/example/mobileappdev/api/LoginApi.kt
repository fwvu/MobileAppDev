package com.example.mobileappdev.api

import com.example.mobileappdev.login.LoginResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {
    @GET("auth/login")
    suspend fun login(@Query("username") username: String, @Query("password") password: String): LoginResponse
}