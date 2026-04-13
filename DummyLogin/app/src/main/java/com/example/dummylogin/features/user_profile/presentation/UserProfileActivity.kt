package com.example.dummylogin.features.user_profile.presentation

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dummylogin.R
import com.example.dummylogin.core.storage.SessionManager
import com.example.dummylogin.databinding.UserProfileBinding

class UserProfileActivity: AppCompatActivity() {

    private lateinit var binding: UserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadUser()
    }

    private fun loadUser(){
        val user = SessionManager(this).getUser()

        binding.etName.setText("${user?.firstName} ${user?.lastName}")
        binding.etEmail.setText(user?.email)

        Glide.with(this)
            .load(user?.image)
            .placeholder(android.R.drawable.sym_def_app_icon)
            .error(android.R.drawable.sym_def_app_icon)
            .circleCrop()
            .into(binding.imgProfile)
    }
}