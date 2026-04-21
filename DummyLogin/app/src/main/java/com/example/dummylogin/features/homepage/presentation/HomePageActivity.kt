package com.example.dummylogin.features.homepage.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.dummylogin.R
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummylogin.core.base.UiState
import com.example.dummylogin.core.storage.SessionManager
import com.example.dummylogin.databinding.HomePageActivityBinding
import com.example.dummylogin.features.cart.presentation.CartActivity
import com.example.dummylogin.features.homepage.data.remote.ProductApi
import com.example.dummylogin.features.homepage.data.remote.RetrofitClient
import com.example.dummylogin.features.homepage.data.repository.ProductRepositoryImpl
import com.example.dummylogin.features.homepage.domain.GetProductsUseCase
import com.example.dummylogin.features.homepage.domain.Product
import com.example.dummylogin.features.product_detail.presentation.ProductDetailActivity
import com.example.dummylogin.features.user_profile.presentation.UserProfileActivity

class HomePageActivity: AppCompatActivity() {
    lateinit var binding: HomePageActivityBinding
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomePageActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupAppBar()
        setupRecycler()
        loadData()
        observeResult()
    }

    fun observeResult(){

        viewModel.state.observe(this) { state ->

            when(state){
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UiState.Success -> {
                    binding.recyclerView.adapter = ProductAdapter(state.data ) {
                        product -> goToDetail(product)
                    }
                    binding.progressBar.visibility = View.GONE
                }
                is UiState.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
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
                R.id.menu_cart -> {
                    goToCartPage()
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

    private fun setupRecycler(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadData(){
        binding.progressBar.visibility = View.VISIBLE
        viewModel.loadProducts();

    }

    private fun setupViewModel(){
        val api = RetrofitClient.api;
        val repository = ProductRepositoryImpl(api);
        val useCase = GetProductsUseCase(repository)

        viewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(useCase)
        )[HomeViewModel::class.java]
    }

    private fun goToDetail(product: Product){
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("product_id", product.id)
        startActivity(intent)
    }

    private fun goToCartPage(){
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }

}