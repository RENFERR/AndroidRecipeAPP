package com.example.coursework.database

import android.content.Context
import com.example.coursework.database.model.recipes.Recipe
import com.example.coursework.database.model.recipes.RecipeStep
import com.example.coursework.database.model.recipes.RecipeType
import kotlinx.coroutines.runBlocking

object DatabaseProductCategoryRecipe {

    fun addRecipeType(context: Context, recipeType: RecipeType) = runBlocking {
        val database = MainBD.getDb(context)
        database.getRecipeDao().addRecipeType(recipeType)
    }

    fun addRecipeStep(context: Context, recipeStep: RecipeStep, recipeID: Int?): Int = runBlocking {
        val database = MainBD.getDb(context)
        database.getRecipeDao().addRecipeStep(recipeStep)


        return@runBlocking 1
    }

    fun addRecipe(context: Context, recipe: Recipe) = runBlocking {
        val database = MainBD.getDb(context)
        database.getRecipeDao().addRecipe(recipe)
    }

    fun updateRecipe(context: Context, recipe: Recipe) = runBlocking {
        val database = MainBD.getDb(context)
        database.getRecipeDao().updateRecipe(recipe)
    }

    fun deleteRecipe(context: Context, recipe: Recipe) = runBlocking {
        val database = MainBD.getDb(context)
        database.getRecipeDao().deleteRecipe(recipe)
    }

}