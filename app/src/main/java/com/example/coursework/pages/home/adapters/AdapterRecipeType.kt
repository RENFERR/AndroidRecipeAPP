package com.example.coursework.pages.home.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.R
import com.example.coursework.database.model.recipes.RecipeType
import com.example.coursework.databinding.ItemCategoryHorizontalCardBinding
import com.example.coursework.helpers.InsertPictureIntoImageView.setImage
import com.example.coursework.pages.popaps.PopupRecipeTypeInfo

class AdapterRecipeType(private val categoryList: List<RecipeType>): RecyclerView.Adapter<AdapterRecipeType.ViewHolder>() {

    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item)  {

        private val binding = ItemCategoryHorizontalCardBinding.bind(item)
        private val contextView by lazy { item.context }

        fun bind(recipeType: RecipeType) = with(binding) {
            recipeType.recipeTypePictureURL?.let { imgCategoryCard.setImage(it) }
            categoryCardTitle.text = recipeType.recipeTypeName
            layoutCategoryCard.setOnClickListener {
                val intent = Intent(contextView, PopupRecipeTypeInfo::class.java)
                intent.putExtra("recipeTypeID", recipeType.recipeTypeID)
                contextView.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_category_horizontal_card, viewGroup, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int = categoryList.size

}