package com.example.gestaopacientes.features.login.data.remote
import com.example.gestaopacientes.features.login.data.dto.LoginRequest
import com.example.gestaopacientes.features.login.data.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body

interface AuthApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequest) : Response<LoginResponse>
}