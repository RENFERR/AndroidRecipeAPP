package com.example.coursework.database

import android.content.Context
import com.example.coursework.database.model.recipes.Recipe
import com.example.coursework.database.model.recipes.RecipeProducts
import com.example.coursework.database.model.recipes.RecipeStep
import com.example.coursework.database.model.recipes.RecipeType
import kotlinx.coroutines.runBlocking

object DatabaseRecipeMethods {

    fun addRecipeType(context: Context, recipeType: RecipeType) = runBlocking {
        val database = MainBD.getDb(context)
        database.getRecipeDao().addRecipeType(recipeType)
    }

    fun addRecipeStep(context: Context, recipeStep: RecipeStep)= runBlocking {
        val database = MainBD.getDb(context)
        database.getRecipeDao().addRecipeStep(recipeStep)
    }

    fun addRecipe(context: Context, recipe: Recipe) = runBlocking {
        val database = MainBD.getDb(context)
        database.getRecipeDao().addRecipe(recipe)
    }

    fun updateRecipe(context: Context, recipe: Recipe) = runBlocking {
        val database = MainBD.getDb(context)
        database.getRecipeDao().updateRecipe(recipe)
    }

    fun updateRecipeStep(context: Context, recipeStep: RecipeStep) = runBlocking {
        val database = MainBD.getDb(context)
        database.getRecipeDao().updateRecipeStep(recipeStep)
    }

    fun deleteRecipe(context: Context, recipe: Recipe) = runBlocking {
        val database = MainBD.getDb(context)
        database.getRecipeDao().deleteRecipe(recipe)
    }

    fun deleteRecipeStep(context: Context, recipeStep: RecipeStep) = runBlocking {
        val database = MainBD.getDb(context)
        database.getRecipeDao().deleteRecipeStep(recipeStep)
    }

    fun getRecipeStepByID(context: Context, recipeStepID: String): RecipeStep? = runBlocking {
        val database = MainBD.getDb(context)
        return@runBlocking database.getRecipeDao().getRecipeStepByID(recipeStepID)
    }

    fun getRecipeStepsByRecipeID(context: Context, recipeID: String): List<RecipeStep> = runBlocking {
        val database = MainBD.getDb(context)
        return@runBlocking database.getRecipeDao().getRecipeStepsByRecipeID(recipeID) ?: listOf()
    }

    fun getRecipeByRecipeTypID(context: Context, recipeTypeID: Int): RecipeType? = runBlocking {
        val database = MainBD.getDb(context)
        return@runBlocking database.getRecipeDao().getRecipeTypeWithID(recipeTypeID)
    }

    fun getRecipeProductsByRecipeID(context: Context, recipeId: String): List<RecipeProducts> = runBlocking {
        val database = MainBD.getDb(context)
        return@runBlocking database.getRecipeDao().getRecipeProductsByRecipeID(recipeId) ?: listOf()
    }

    fun getRecipeByID(context: Context, recipeId: String): Recipe? = runBlocking {
        val database = MainBD.getDb(context)
        return@runBlocking database.getRecipeDao().getRecipeByID(recipeId)
    }
}