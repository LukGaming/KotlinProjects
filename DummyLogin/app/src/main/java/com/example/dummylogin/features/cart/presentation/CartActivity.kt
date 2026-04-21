package com.example.dummylogin.features.cart.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dummylogin.databinding.CartActivityBinding

class CartActivity: AppCompatActivity() {
    lateinit var binding: CartActivityBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CartActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setOnClickListener(::goBack)
        loadCartItems()

    }

    private fun goBack(view: View){
        finish()
    }

    private fun loadCartItems(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = CartAdapter()

    }
}