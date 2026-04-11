package com.example.dummylogin.features.auth.data.remote

import com.example.dummylogin.features.auth.data.model.LoginRequest
import com.example.dummylogin.features.auth.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body

interface AuthService {
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ) : Response<LoginResponse>
}