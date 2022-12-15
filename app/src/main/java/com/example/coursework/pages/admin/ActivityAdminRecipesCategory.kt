package com.example.coursework.pages.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.coursework.database.DatabaseProductCategoryRecipe.addRecipeType
import com.example.coursework.database.model.recipes.RecipeType
import com.example.coursework.databinding.ActivityAdminRecipesCategoryBinding

class ActivityAdminRecipesCategory : AppCompatActivity() {

    private lateinit var binding: ActivityAdminRecipesCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminRecipesCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddRecipeCategory.setOnClickListener { addRecipeCategory() }
    }

    private fun addRecipeCategory() = with(binding) {
        val recipeTypeName = inputEditTextRecipeCategoryName.text?.toString()
        val recipeTypeDescription = inputEditTextProductCategoryDescription.text?.toString()
        val recipeTypePictureURL = inputEditTextProductCategoryPictureUrl.text?.toString()

        inputLayoutRecipeCategoryName.error = "Обязательное поле не заполнено"

        if (!recipeTypeName.isNullOrEmpty()) {
            inputLayoutRecipeCategoryName.isErrorEnabled = false
            val recipeType = RecipeType(null, recipeTypeName, recipeTypeDescription, recipeTypePictureURL)
            addRecipeType(this@ActivityAdminRecipesCategory, recipeType)
            inputEditTextRecipeCategoryName.text?.clear()
            inputEditTextProductCategoryDescription.text?.clear()
            inputEditTextProductCategoryPictureUrl.text?.clear()
            Toast.makeText(
                this@ActivityAdminRecipesCategory,
                "Категория $recipeTypeName была добавлена",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            inputLayoutRecipeCategoryName.isErrorEnabled = true
        }
    }
}