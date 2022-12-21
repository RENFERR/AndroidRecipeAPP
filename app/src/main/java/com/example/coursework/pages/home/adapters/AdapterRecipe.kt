package com.example.coursework.pages.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.R
import com.example.coursework.database.model.recipes.Recipe
import com.example.coursework.databinding.ItemRecipeHorizontalCardBinding
import com.example.coursework.helpers.InsertPictureIntoImageView.setImage
import com.example.coursework.helpers.Transitions

class AdapterRecipe(private val recipeList: List<Recipe>): RecyclerView.Adapter<AdapterRecipe.ViewHolder>() {

    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item)  {
        private val binding = ItemRecipeHorizontalCardBinding.bind(item)
        private val contextView = item.context

        fun bind(recipe: Recipe) = with(binding) {
            recipe.recipePictureURL?.let { imgRecipeCard.setImage(it) }
            recipeCardTitle.text = recipe.recipeName
            layoutRecipeCard.setOnClickListener {
                Transitions(contextView).goToRecipeInfo(recipe.recipeID)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_recipe_horizontal_card, viewGroup, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int = recipeList.size

}