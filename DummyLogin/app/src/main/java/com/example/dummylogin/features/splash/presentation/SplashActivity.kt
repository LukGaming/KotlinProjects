package com.example.dummylogin.features.splash.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dummylogin.R
import com.example.dummylogin.core.storage.SessionManager
import com.example.dummylogin.features.auth.presentation.LoginActivity
import com.example.dummylogin.features.homepage.presentation.HomePageActivity

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spash_activity)
        verifyLoggedIn()
    }

    fun verifyLoggedIn(){
        val sessionManager = SessionManager(this)
        if(sessionManager.isLogged()){
            goToHome()
        }else{
            goToLogin()
        }
        finish()
    }

    fun goToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun goToHome(){
        val intent = Intent(this, HomePageActivity::class.java)
        startActivity(intent)
    }
}