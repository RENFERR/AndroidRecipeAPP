package com.example.coursework.pages.popaps.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.R
import com.example.coursework.database.DatabaseProductsMethods.getProductsByRecipeID
import com.example.coursework.database.DatabaseRecipeMethods
import com.example.coursework.database.DatabaseRecipeMethods.getRecipeProductsByRecipeID
import com.example.coursework.database.model.recipes.Recipe
import com.example.coursework.databinding.ItemPopupRecipeBinding
import com.example.coursework.helpers.InsertPictureIntoImageView.setImage
import com.example.coursework.helpers.Transitions

class AdapterPopupRecipes(private val recipeList: List<Recipe>): RecyclerView.Adapter<AdapterPopupRecipes.ViewHolder>() {

    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item) {

        private val binding = ItemPopupRecipeBinding.bind(item)
        private val contextView = item.context

        fun bind(recipe: Recipe) = with(binding) {
            recipe.recipePictureURL?.let { imageView.setImage(it) }
            itemPopupRecipeTitle.text = recipe.recipeName
            itemPopupProductDescription.text = recipe.recipeDescription
            itemPopupProductCalories.text = "Калорийность: ${getRecipeCalories(recipe.recipeID)}"
            cardViewItemPopUp.setOnClickListener { Transitions(contextView).goToRecipeInfo(recipe.recipeID) }
        }

        private fun getRecipeCalories(recipeID: String): Double {
            val productList = getProductsByRecipeID(contextView, recipeID)
            val recipeProductList = getRecipeProductsByRecipeID(contextView, recipeID)
            var calories = 0.0
            for (product in productList) {
                for (recipeProduct in recipeProductList) {
                    if (recipeProduct.productID == product.productID) {
                        calories += product.calories * recipeProduct.productCount / 100
                    }
                }
            }
            return calories
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_popup_recipe, viewGroup, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int = recipeList.size
}