package com.example.dummylogin.features.homepage.presentation


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dummylogin.databinding.ItemProductBinding
import com.example.dummylogin.features.homepage.domain.Product
import com.example.dummylogin.features.product_detail.presentation.ProductDetailActivity

class ProductAdapter(
    private val items: List<Product>,
    private val onClick: (Product) -> Unit) : RecyclerView.Adapter<ProductAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.binding.tvTitle.text = item.title
        holder.binding.tvPrice.text = "R$ ${item.price}"
        holder.binding.tvRating.text = "⭐ ${item.rating}"

        Glide.with(holder.itemView)
            .load(item.thumbnail)
            .into(holder.binding.imgProduct)

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }
}