package com.example.dummylogin.features.auth.data.repositories

import com.example.dummylogin.features.auth.data.model.LoginRequest
import com.example.dummylogin.features.auth.data.model.LoginResponse
import com.example.dummylogin.features.auth.data.remote.AuthService

class AuthRepository(private val service: AuthService) {

    suspend fun login(email: String, password: String) : Result<LoginResponse>{
        return try {
            val response = service.login(LoginRequest(email, password))
            if(response.isSuccessful){
                Result.success(response.body()!!)
            }else{
                Result.failure(Exception("Ocorreu um erro ao efetuar login."))
            }
        } catch(e: Exception){
            return Result.failure(e)
        }
    }
}