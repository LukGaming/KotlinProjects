package com.example.dummylogin.features.auth.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummylogin.core.base.UiState
import com.example.dummylogin.core.storage.SessionManager
import com.example.dummylogin.features.auth.domain.usecases.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase, private val sessionManager: SessionManager) : ViewModel() {
    val state = MutableLiveData<UiState<Unit>>()

    fun login(email: String, password: String){
        viewModelScope.launch {
            state.value = UiState.Loading
            val result = loginUseCase(email, password)

            state.value = result.fold(
                onSuccess = {
                    sessionManager.saveToken(it.token)
                    UiState.Success(Unit)
                }, onFailure = {
                    UiState.Error(it.message ?: "Erro")
                }
            )
        }
    }


}