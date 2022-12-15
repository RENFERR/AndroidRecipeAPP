package com.example.coursework.pages.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coursework.database.DatabaseProductCategoryRecipe.addRecipe
import com.example.coursework.database.DatabaseProductCategoryRecipe.updateRecipe
import com.example.coursework.database.model.recipes.Recipe
import com.example.coursework.databinding.ActivityAddRecipeBinding
import java.util.UUID

class ActivityAddRecipe : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecipeBinding
    private val recipeUuid by lazy { UUID.randomUUID().toString() }
    private val iniRecipe by lazy { Recipe(recipeUuid, null, null, null, null) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniRecipe()
    }

    private fun iniRecipe() {
        editIniRecipe(addRecipe(this@ActivityAddRecipe, iniRecipe).recipeID)
    }

    private fun editIniRecipe(recipeID: String) = with(binding) {
        val recipeName = inputEditTextRecipeName.text?.toString()
        val recipeType = 135
        val recipePictureURL = inputEditTextProductPictureUrl.text?.toString()
        val recipeDescription = "asdasd"

        binding.buttonAddRecipe.setOnClickListener { updateRecipe(
            this@ActivityAddRecipe,
            Recipe(
                recipeID,
                recipeType,
                recipeName,
                recipePictureURL,
                recipeDescription
            )
        ) }
    }
}