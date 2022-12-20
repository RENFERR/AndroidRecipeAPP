package com.example.coursework.pages.recipes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coursework.database.DatabaseProductCategoryRecipe.addRecipe
import com.example.coursework.database.DatabaseProductCategoryRecipe.addRecipeStep
import com.example.coursework.database.DatabaseProductsMethods.addRecipeProduct
import com.example.coursework.database.MainBD
import com.example.coursework.database.model.recipes.Recipe
import com.example.coursework.database.model.recipes.RecipeProducts
import com.example.coursework.database.model.recipes.RecipeStep
import com.example.coursework.databinding.ActivityAddRecipeBinding
import com.example.coursework.helpers.Transitions
import com.example.coursework.pages.recipes.adapters.AdapterAddRecipeProduct
import com.example.coursework.pages.recipes.adapters.AdapterAddRecipeProduct.Companion.productsList
import com.example.coursework.pages.recipes.adapters.AdapterAddRecipeSteps
import com.example.coursework.pages.recipes.adapters.AdapterAddRecipeSteps.Companion.stepsList
import com.example.coursework.pages.recipes.adapters.AdapterSpinnerRecipeTypes
import java.util.*

class ActivityAddRecipe : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecipeBinding
    private val transitions by lazy { Transitions(this@ActivityAddRecipe) }
    private val iniRecipe by lazy { Recipe(UUID.randomUUID().toString(), null, null, null, null) }
    private var pickedRecipeTypeID: Int? = null
    private val database by lazy { MainBD.getDb(this@ActivityAddRecipe) }
    private val adapterAddRecipeSteps by lazy { AdapterAddRecipeSteps() }
    private val adapterAddRecipeProduct by lazy { AdapterAddRecipeProduct() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniRecipe()
    }

    private fun iniRecipe() {
        database.getRecipeDao().getAllRecipeTypes()?.asLiveData()?.observe(this@ActivityAddRecipe) { recipeTypesList ->
            AdapterSpinnerRecipeTypes(binding.spinnerProductType, recipeTypesList) {
                pickedRecipeTypeID = it.recipeTypeID
            }.iniSpinner()
        }
        editIniRecipe()
    }

    private fun editIniRecipe() = with(binding) {
        recyclerViewRecipeSteps.apply {
            layoutManager = LinearLayoutManager(this@ActivityAddRecipe)
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
            this.adapter = adapterAddRecipeSteps
        }
        recyclerviewRecipeProducts.apply {
            this.adapter = adapterAddRecipeProduct
        }
        buttonAddRecipeProduct.setOnClickListener {
            val iniRecipeProduct = RecipeProducts(null, iniRecipe.recipeID, null, 0.0)
            adapterAddRecipeProduct.addRecipeProduct(iniRecipeProduct)
        }
        buttonAddRecipeStep.setOnClickListener {
            val iniRecipeStep = RecipeStep(null, iniRecipe.recipeID, "", "", "")
            adapterAddRecipeSteps.addRecipeStepToAdapter(iniRecipeStep)
        }
        buttonAddRecipe.setOnClickListener {
            val recipeName = inputEditTextRecipeName.text?.toString()
            val recipePictureURL = inputEditTextProductPictureUrl.text?.toString()
            val recipeDescription = inputEditTextProductDescription.text?.toString()
            if (stepsList.isNotEmpty()) {
                if (productsList.isNotEmpty()) {
                    if (!recipeName.isNullOrEmpty()) {
                        iniRecipe.recipeName = recipeName
                        iniRecipe.recipeDescription = recipeDescription
                        iniRecipe.recipePictureURL = recipePictureURL
                        iniRecipe.recipeTypeID = pickedRecipeTypeID
                        addRecipe(this@ActivityAddRecipe, iniRecipe)
                        stepsList.forEach {
                            addRecipeStep(this@ActivityAddRecipe, it)
                        }
                        productsList.forEach {
                            addRecipeProduct(this@ActivityAddRecipe, it)
                        }
                        if (closeKeyboard(binding.buttonAddRecipeStep)) {
                            stepsList.clear()
                            productsList.clear()
                        }
                        Toast.makeText(
                            this@ActivityAddRecipe,
                            "Вы успешно добавили рецепт $recipeName",
                            Toast.LENGTH_SHORT
                        ).show()
                        transitions.goToHomePage()
                    } else {
                        Toast.makeText(
                            this@ActivityAddRecipe,
                            "Задайте название рецепту",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@ActivityAddRecipe,
                        "невозможно создать рецепт без продуктво",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@ActivityAddRecipe,
                    "Нельзя добавить рецепт без шагов",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun closeKeyboard(view: View): Boolean {
        return try {
            val imm: InputMethodManager = this@ActivityAddRecipe.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            true
        } catch (e: Exception) {
            Log.e("Error", "FragmentAddRecipeStep -> closeKeyboard: ", e)
            false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Возращение на экран с заявками из шапки (стрелочка назад)
        if (item.itemId == android.R.id.home) showAlertExit()
        return true
    }
    override fun onBackPressed() {
        showAlertExit()
    }

    private fun showAlertExit() {
        AlertDialog.Builder(this).apply {
            setTitle("Вы не сохранили рецепт")
            setMessage("Вы точно хотите выйти и потерять данные?")
            setPositiveButton(android.R.string.yes) { dialog, which ->
                transitions.goToHomePage()
            }
            setNegativeButton(android.R.string.no) { dialog, which -> }
            show()
        }
    }
}