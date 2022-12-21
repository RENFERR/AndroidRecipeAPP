package com.example.coursework.pages.recipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.R
import com.example.coursework.database.model.recipes.RecipeStep
import com.example.coursework.databinding.ItemRecipeStepBinding
import com.example.coursework.helpers.InsertPictureIntoImageView.setImage

class AdapterRecipeStep(private val stepList: List<RecipeStep>): RecyclerView.Adapter<AdapterRecipeStep.ViewHolder>() {

    inner class ViewHolder(item:View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemRecipeStepBinding.bind(item)

        fun bind(step: RecipeStep) = with(binding) {
            itemRecipeStepRecipeName.text = "Шаг ${absoluteAdapterPosition + 1}. ${step.recipeStepTitle}"
            itemRecipeStepDescription.text = step.recipeStepDescription
            itemRecipeStepPicture.setImage(step.recipeStepPictureURL ?: "")
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_recipe_step, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stepList[position])
    }

    override fun getItemCount(): Int = stepList.size
}