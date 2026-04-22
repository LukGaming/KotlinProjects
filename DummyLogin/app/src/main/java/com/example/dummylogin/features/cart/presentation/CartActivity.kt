package com.example.dummylogin.features.cart.presentation

import CartAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.example.dummylogin.databinding.CartActivityBinding
import com.example.dummylogin.features.cart.CartManager

class CartActivity: AppCompatActivity() {
    lateinit var binding: CartActivityBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CartAdapter

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
        val items = CartManager.getItems();
        verifyEmptyState()
        adapter = CartAdapter(items = CartManager.getItems(), onClick = {}, onRemove = {item ->
            CartManager.removeProduct(item.productId)
            verifyEmptyState()
            adapter.notifyDataSetChanged()
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun verifyEmptyState(){
        val items = CartManager.getItems();
        if(items.isEmpty()){
            binding.emptyState.visibility = View.VISIBLE
        }
        else{
            binding.emptyState.visibility = View.GONE
        }
    }

}