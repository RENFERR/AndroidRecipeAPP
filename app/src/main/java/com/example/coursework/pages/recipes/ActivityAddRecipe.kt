package com.example.coursework.pages.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.asLiveData
import com.example.coursework.database.DatabaseProductCategoryRecipe.addRecipe
import com.example.coursework.database.DatabaseProductCategoryRecipe.deleteRecipe
import com.example.coursework.database.DatabaseProductCategoryRecipe.updateRecipe
import com.example.coursework.database.MainBD
import com.example.coursework.database.model.recipes.Recipe
import com.example.coursework.databinding.ActivityAddRecipeBinding
import com.example.coursework.pages.recipes.adapters.AdapterSpinnerRecipeTypes
import kotlinx.coroutines.runBlocking
import java.util.UUID

class ActivityAddRecipe : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecipeBinding
    private val recipeUuid by lazy { UUID.randomUUID().toString() }
    private val iniRecipe by lazy { Recipe(recipeUuid, null, null, null, null) }
    private var pickedRecipeTypeID: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniRecipe()
    }

    private fun iniRecipe() {
        val database = MainBD.getDb(this@ActivityAddRecipe)
        database.getRecipeDao().getAllRecipeTypes()?.asLiveData()?.observe(this@ActivityAddRecipe) { recipeTypesList ->
            AdapterSpinnerRecipeTypes(binding.spinnerProductType, recipeTypesList) {
                pickedRecipeTypeID = it.recipeTypeID
            }.iniSpinner()
        }
        addRecipe(this@ActivityAddRecipe, iniRecipe)
        editIniRecipe(iniRecipe.recipeID)
    }

    private fun editIniRecipe(recipeID: String) = with(binding) {
        val recipeName = inputEditTextRecipeName.text?.toString()
        val recipePictureURL = inputEditTextProductPictureUrl.text?.toString()
        val recipeDescription = inputEditTextProductDescription.text?.toString()

        binding.buttonAddRecipe.setOnClickListener { updateRecipe(
            this@ActivityAddRecipe,
            Recipe(
                recipeID,
                pickedRecipeTypeID,
                recipeName,
                recipePictureURL,
                recipeDescription
            )
        ) }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("Вы не сохранили рецепт")
            setMessage("Вы точно хотите выйти и потерять данные?")
            setPositiveButton(android.R.string.yes) { dialog, which ->
                deleteRecipe(this@ActivityAddRecipe, iniRecipe)
            }
        }
        super.onBackPressed()
    }
}