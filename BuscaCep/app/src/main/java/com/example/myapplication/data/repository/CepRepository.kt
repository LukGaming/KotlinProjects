package com.example.myapplication.data.repository

import com.example.myapplication.data.Models.CepResponse;
import com.example.myapplication.data.remote.CepService


class CepRepository {
    var cepService: CepService()
    suspend fun getCep(cep: String): CepResponse {
        return cepService.getCep(cep)
    }
}