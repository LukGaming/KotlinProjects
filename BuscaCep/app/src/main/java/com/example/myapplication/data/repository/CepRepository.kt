package com.example.myapplication.data.repository

import com.example.myapplication.CepResponse;


class CepRepository {
    suspend fun getCep(cep: String): CepResponse {
        throw Exception("ocorreu um erro ao buscar cep")
    }
}