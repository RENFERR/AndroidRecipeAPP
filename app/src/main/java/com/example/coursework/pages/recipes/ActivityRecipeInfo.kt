package com.example.coursework.pages.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coursework.database.DatabaseProductsMethods.getProductsByRecipeID
import com.example.coursework.database.DatabaseRecipeMethods.getRecipeByID
import com.example.coursework.database.DatabaseRecipeMethods.getRecipeByRecipeTypID
import com.example.coursework.database.DatabaseRecipeMethods.getRecipeProductsByRecipeID
import com.example.coursework.database.DatabaseRecipeMethods.getRecipeStepsByRecipeID
import com.example.coursework.database.model.recipes.RecipeStep
import com.example.coursework.databinding.ActivityRecipeInfoBinding
import com.example.coursework.helpers.InsertPictureIntoImageView.setImage
import com.example.coursework.pages.recipes.adapters.AdapterRecipeStep

class ActivityRecipeInfo : AppCompatActivity() {

    lateinit var binding: ActivityRecipeInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val recipeID = bundle?.getString("recipeID", "")
        if (!recipeID.isNullOrEmpty()) {
            setDescription(recipeID)
            setRecyclerView(getRecipeStepsByRecipeID(this@ActivityRecipeInfo, recipeID))
        } else {
            Toast.makeText(this@ActivityRecipeInfo, "Не удалось найти рецепт", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDescription(recipeID: String) = with(binding) {
        val recipe = getRecipeByID(this@ActivityRecipeInfo, recipeID)
        if (recipe != null) {
            val recipeCategory = recipe.recipeTypeID?.let { getRecipeByRecipeTypID(this@ActivityRecipeInfo, it) }
            val name = if (recipe.recipeName.isNullOrEmpty()) "Нет наименования" else recipe.recipeName
            val categoryName = if (recipeCategory?.recipeTypeName.isNullOrEmpty()) "Без категории" else "Категория: ${recipeCategory?.recipeTypeName}"
            val description = if (recipe.recipeDescription.isNullOrEmpty()) "Нет описания" else "Описание: ${recipe.recipeDescription}"
            val pictureURL = recipe.recipePictureURL

            recipeTitle.text = name
            recipeDescription.text = description
            binding.recipeCategory.text = categoryName
            recipeProducts.text = "Продукты: ${getProducts(recipeID)}"
            if (pictureURL != null) {
                recipePicture.setImage(pictureURL)
            } else {
                recipePicture.visibility = View.GONE
            }
        } else {
            Toast.makeText(this@ActivityRecipeInfo, "Не удалось найти рецепт", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getProducts(recipeID: String): String {
        var products = ""
        val productList = getProductsByRecipeID(this@ActivityRecipeInfo, recipeID)
        val recipeProducts = getRecipeProductsByRecipeID(this@ActivityRecipeInfo, recipeID)
        for (item in productList) {
            for (recipeProduct in recipeProducts) {
                if (item.productID == recipeProduct.productID) products += "Название: ${item.name}. Количество: ${recipeProduct.productCount}г.\n"
            }
        }
        return products.ifEmpty { "Нет продуктов" }
    }

    private fun setRecyclerView(recipeList: List<RecipeStep>) {
        binding.recyclerViewRecipeSteps.apply {
            layoutManager = LinearLayoutManager(this@ActivityRecipeInfo)
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
            this.adapter = AdapterRecipeStep(recipeList)
        }
    }
}