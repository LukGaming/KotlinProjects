package com.example.dummylogin.features.auth.domain.usecases

import com.example.dummylogin.features.auth.data.AuthRepository

class LoginUseCase( private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = authRepository.login(email, password)
}