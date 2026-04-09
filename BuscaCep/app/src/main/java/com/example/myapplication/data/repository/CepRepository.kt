package com.example.myapplication.data.repository

import com.example.myapplication.CepResponse
import com.example.myapplication.data.remote.RetrofitClient

class CepRepository {
    suspend fun getCep(cep: String) : CepResponse {
        return RetrofitClient.instance.getCep(cep)
    }
}