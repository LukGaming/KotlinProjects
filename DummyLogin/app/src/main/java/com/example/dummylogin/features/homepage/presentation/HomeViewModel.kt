package com.example.dummylogin.features.homepage.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dummylogin.core.base.UiState
import com.example.dummylogin.features.homepage.domain.GetProductsUseCase
import com.example.dummylogin.features.homepage.domain.Product
import kotlinx.coroutines.launch

class HomeViewModel(private val getProductsUseCase: GetProductsUseCase): ViewModel() {
    val state = MutableLiveData<UiState<List<Product>>>()

    fun loadProducts() {
        viewModelScope.launch {
            state.value = UiState.Loading

            val result = getProductsUseCase()

            state.value = result.fold(
                onSuccess = {
                    UiState.Success(it)
                },
                onFailure = {
                    UiState.Error(it.message ?: "Erro ao carregar")
                }
            )
        }
    }
}

class HomeViewModelFactory(private val useCase: GetProductsUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(useCase) as T
    }
}