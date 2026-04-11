package com.example.dummylogin.features.auth.domain.usecases

import com.example.dummylogin.features.auth.data.repositories.AuthRepository

class LoginUseCase( private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = authRepository.login(email, password)
}