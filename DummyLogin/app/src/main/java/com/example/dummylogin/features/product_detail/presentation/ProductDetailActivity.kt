package com.example.dummylogin.features.product_detail.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dummylogin.core.base.UiState
import com.example.dummylogin.databinding.ProductDetailBinding
import com.example.dummylogin.features.cart.CartManager
import com.example.dummylogin.features.homepage.data.remote.RetrofitClient
import com.example.dummylogin.features.homepage.data.repository.ProductRepositoryImpl
import com.example.dummylogin.features.homepage.domain.Product
import com.example.dummylogin.features.product_detail.domain.GetProductByIdUseCase

class ProductDetailActivity: AppCompatActivity() {
    lateinit var viewModel: ProductDetailViewModel

    lateinit var binding: ProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setOnClickListener(::finishScren)
        setupViewModel()
        observeViewModel()
        getProductById()
        binding.pushToCartButton.setOnClickListener(::addProductToCart)
    }

    private fun observeViewModel(){
        viewModel.state.observe(this){
            println("Escutando VIewModeL: ${it}")


            when(it){
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UiState.Success -> {
                    val product = it.data
                    binding.tvTitle.text = product.title
                    binding.tvPrice.text = "R$ ${product.price}"
                    binding.tvRating.text = "⭐ ${product.rating}"

                    Glide.with(binding.imgProduct)
                        .load(product.thumbnail)
                        .into(binding.imgProduct)

                    binding.progressBar.visibility = View.GONE
                }
                is UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun setupViewModel(){
        val api = RetrofitClient.api;
        val repository = ProductRepositoryImpl(api)
        val useCase = GetProductByIdUseCase(repository)
        viewModel = ViewModelProvider(
            this,
            ProductDetailViewModelFactory(useCase)
        )[ProductDetailViewModel::class.java]
    }

    private fun getProductById(){
       val productId = intent.getIntExtra("product_id", -1);

        viewModel.getProductById(productId)
    }

    private fun finishScren(view: View){
        finish()
    }

    private fun addProductToCart(view: View){
            val product = viewModel.currentProduct

        if(product != null){
            CartManager.addProduct(product)
        }

    }
}