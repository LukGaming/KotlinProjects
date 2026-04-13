package com.example.dummylogin.features.homepage.presentation

import android.content.Intent
import android.os.Bundle
import com.example.dummylogin.R
import androidx.appcompat.app.AppCompatActivity
import com.example.dummylogin.core.storage.SessionManager
import com.example.dummylogin.databinding.HomePageActivityBinding
import com.example.dummylogin.features.user_profile.presentation.UserProfileActivity

class HomePageActivity: AppCompatActivity() {
    lateinit var binding: HomePageActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HomePageActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupAppBar()
    }

    fun setupAppBar(){
        val user = SessionManager(this).getUser()
        binding.toolbar.title = "Olá, ${user?.firstName}"
        binding.toolbar.setOnMenuItemClickListener { item ->

            when(item.itemId){
                R.id.menu_profile -> {
                    goToProfile()
                    true
                }

                else -> false
            }

        }
    }

    fun goToProfile(){
        val intent = Intent(this, UserProfileActivity::class.java)
        startActivity(intent)

    }
}