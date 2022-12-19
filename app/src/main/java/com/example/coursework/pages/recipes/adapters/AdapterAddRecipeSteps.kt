package com.example.coursework.pages.recipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.R
import com.example.coursework.database.model.recipes.RecipeStep
import com.example.coursework.databinding.ItemRecipeStepBinding
import com.example.coursework.helpers.SimpleTextWatcher

class AdapterAddRecipeSteps: RecyclerView.Adapter<AdapterAddRecipeSteps.ViewHolder>() {

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = ItemRecipeStepBinding.bind(item)

        fun bind(recipeStep: RecipeStep) = with(binding) {
            setTextListener(recipeStep)
            tieRecipeStepTitle.setText(recipeStep.recipeStepTitle)
            tieRecipeStepDescription.setText(recipeStep.recipeStepDescription)
            tieRecipeStepPictureUrl.setText(recipeStep.recipeStepPictureURL)
            textViewStepTitle.text = "Шаг #${adapterPosition+1}"
            buttonDeleteRecipeStep.setOnClickListener {
                stepsList.remove(recipeStep)
                notifyDataSetChanged()
            }
        }

        private fun setTextListener(recipeStep: RecipeStep) = with(binding) {
            tieRecipeStepTitle.addTextChangedListener(object : SimpleTextWatcher() {
                override fun onTextChanged(field: CharSequence?, start: Int, before: Int, count: Int) {
                    field?.toString()?.let { valueOfField ->
                        recipeStep.recipeStepTitle = valueOfField
                    }
                }
            })
            tieRecipeStepDescription.addTextChangedListener(object : SimpleTextWatcher() {
                override fun onTextChanged(field: CharSequence?, start: Int, before: Int, count: Int) {
                    field?.toString()?.let { valueOfField ->
                        recipeStep.recipeStepDescription = valueOfField
                    }
                }
            })
            tieRecipeStepPictureUrl.addTextChangedListener(object : SimpleTextWatcher() {
                override fun onTextChanged(field: CharSequence?, start: Int, before: Int, count: Int) {
                    field?.toString()?.let { valueOfField ->
                        recipeStep.recipeStepPictureURL = valueOfField
                    }
                }
            })
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_recipe_step, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stepsList[position])
    }

    override fun getItemCount(): Int = stepsList.size

    fun addRecipeStepToAdapter(recipeStep: RecipeStep) {
        stepsList.apply {
            add(recipeStep)
        }
        notifyItemInserted(stepsList.count())
    }

    companion object {
        val stepsList = ArrayList<RecipeStep>()
    }
}