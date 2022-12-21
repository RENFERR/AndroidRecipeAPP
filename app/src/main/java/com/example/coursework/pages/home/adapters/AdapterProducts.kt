package com.example.coursework.pages.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.R
import com.example.coursework.database.model.products.Product
import com.example.coursework.databinding.ItemProductHorizontalCardBinding
import com.example.coursework.helpers.InsertPictureIntoImageView.setImage

class AdapterProducts(private val productsList: List<Product>): RecyclerView.Adapter<AdapterProducts.ViewHolder>() {

    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item)  {
        private val binding = ItemProductHorizontalCardBinding.bind(item)

        fun bind(product: Product) = with(binding) {
            product.pictureURL?.let { imgProductCard.setImage(it) }
            productCardTitle.text = product.name
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_product_horizontal_card, viewGroup, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productsList[position])
    }

    override fun getItemCount(): Int = productsList.size
}