package com.example.myapplication.data.remote

import com.example.myapplication.data.Models.CepResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CepService {
    @GET("ws/{cep}/json")
    suspend fun getCep(@Path("cep") cep: String): CepResponse
}