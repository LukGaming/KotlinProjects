package com.example.dummylogin.features.auth.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dummylogin.core.base.UiState
import com.example.dummylogin.core.storage.SessionManager
import com.example.dummylogin.features.auth.data.repositories.AuthRepository
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
                    sessionManager.saveUser(it)
                    UiState.Success(Unit)
                }, onFailure = {
                    UiState.Error(it.message ?: "Erro")
                }
            )
        }
    }


}

class LoginViewModelFactory(
    private val loginUseCase: LoginUseCase,
    private val sessionManager: SessionManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(loginUseCase, sessionManager) as T
    }
}