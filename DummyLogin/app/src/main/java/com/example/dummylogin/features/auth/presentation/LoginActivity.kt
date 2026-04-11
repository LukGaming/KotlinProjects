package com.example.dummylogin.features.auth.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.dummylogin.R
import com.example.dummylogin.core.base.UiState
import com.example.dummylogin.core.network.RetrofitClient
import com.example.dummylogin.core.storage.SessionManager
import com.example.dummylogin.features.auth.data.repositories.AuthRepository
import com.example.dummylogin.features.auth.data.remote.AuthService
import com.example.dummylogin.features.auth.domain.usecases.LoginUseCase
import kotlin.math.log

class LoginActivity: AppCompatActivity() {


    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var etError: TextView
    lateinit var loginProgress: ProgressBar

    private  val viewModel: LoginViewModel by viewModels {
        val service = RetrofitClient.instance.create(AuthService::class.java)
        val repository = AuthRepository(service)
        val useCase = LoginUseCase(repository)
        val sessionManager = SessionManager(this)

        LoginViewModelFactory(useCase, sessionManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        setupBindings()
        setupObservers()
    }

    fun setupBindings(){
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        etError = findViewById(R.id.etError)
        loginProgress = findViewById(R.id.loginProgress)
        btnLogin.setOnClickListener(::validateLogin)
    }


    fun validateLogin(view: View) {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        if(email.isBlank() || password.isBlank()){
            setError("Por favor, preencha usuários e senha.")
            return
        }
        else{
            viewModel.login(email, password)
        }
    }

    fun resetVisibilities(){
        etError.text = ""
        etError.visibility = View.GONE
        btnLogin.visibility = View.VISIBLE
        loginProgress.visibility = View.GONE
    }

    fun setError(error: String) {
        etError.text = error
        etError.visibility = View.VISIBLE
    }

    fun setLoadingVisibility(){
        btnLogin.visibility = View.GONE
        loginProgress.visibility = View.VISIBLE
    }

    fun setupObservers(){
        viewModel.state.observe(this) {
            state ->
            when(state){
                is UiState.Loading -> setLoadingVisibility()
                is UiState.Success -> {
                    resetVisibilities()
                    navigateToHome()
                }
                is UiState.Error -> {
                    resetVisibilities()
                    setError(state.message)
                }
            }


        }
    }

    fun navigateToHome(){
        Log.i("LOGIN", "Login sucesso!")
    }
}