package com.example.dummylogin.features.user_profile.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dummylogin.R
import com.example.dummylogin.core.storage.SessionManager
import com.example.dummylogin.databinding.UserProfileBinding
import com.example.dummylogin.features.auth.presentation.LoginActivity

class UserProfileActivity: AppCompatActivity() {

    private lateinit var binding: UserProfileBinding

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)
        binding = UserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadUser()
        binding.toolbar.setOnClickListener(::finishScren)

        binding.btnLogout.setOnClickListener(::logout)
    }

    private fun loadUser(){
        val user = sessionManager.getUser()

        binding.etName.text = getString(
            R.string.greeting_user,
            user?.firstName,
            user?.lastName
        )
        binding.etEmail.text = user?.email

        Glide.with(this)
            .load(user?.image)
            .placeholder(android.R.drawable.sym_def_app_icon)
            .error(android.R.drawable.sym_def_app_icon)
            .circleCrop()
            .into(binding.imgProfile)
    }

    private fun logout(view: View){
        sessionManager.clear()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finishScren(view)
    }

    private fun finishScren(view: View){
        finish()
    }
}