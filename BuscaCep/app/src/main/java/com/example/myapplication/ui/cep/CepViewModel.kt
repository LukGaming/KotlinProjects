package com.example.myapplication.ui.cep

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.CepResponse
import com.example.myapplication.data.repository.CepRepository
import kotlinx.coroutines.launch

class CepViewModel : ViewModel() {
    private val repository = CepRepository()

    val cepResult = MutableLiveData<CepResponse>();
    val error = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun buscarCep(cep: String){
        viewModelScope.launch {

            loading.value = true;

            try {
                val response = repository.getCep(cep);
                cepResult.value = response
            } catch(e: Exception){
                error.value = "Erro ao buscar CEp"
            } finally {
                loading.value = false
            }
        }
    }
}