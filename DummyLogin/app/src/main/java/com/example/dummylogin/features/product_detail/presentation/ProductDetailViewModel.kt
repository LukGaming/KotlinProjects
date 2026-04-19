package com.example.dummylogin.features.product_detail.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dummylogin.core.base.UiState
import com.example.dummylogin.features.homepage.data.repository.ProductRepository
import com.example.dummylogin.features.homepage.domain.Product
import com.example.dummylogin.features.product_detail.domain.GetProductByIdUseCase
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val useCase: GetProductByIdUseCase
): ViewModel() {

    val state = MutableLiveData<UiState<Product>>();

    fun getProductById(id: Int){
        viewModelScope.launch {
            state.value = UiState.Loading

            val response = useCase(id);

            state.value = response.fold(
                onSuccess = {
                    UiState.Success(it)
                },
                onFailure = {
                    UiState.Error(it.toString())
                }
            )
        }
    }
}

class ProductDetailViewModelFactory(private val useCase: GetProductByIdUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductDetailViewModel(useCase) as T
    }
}