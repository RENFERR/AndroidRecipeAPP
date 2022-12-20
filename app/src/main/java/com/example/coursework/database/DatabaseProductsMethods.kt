package com.example.coursework.database

import android.content.Context
import com.example.coursework.database.model.products.Product
import com.example.coursework.database.model.recipes.RecipeProducts
import kotlinx.coroutines.runBlocking

object DatabaseProductsMethods {

    fun addNewProduct(context: Context, product: Product) = runBlocking {
        val database = MainBD.getDb(context)
        database.getProductDao().addProduct(product)
    }

    fun addRecipeProduct(context: Context, recipeProducts: RecipeProducts) = runBlocking {
        val database = MainBD.getDb(context)
        database.getProductDao().addRecipeProduct(recipeProducts)
    }

    fun getRecipeProductsByRecipeID(context: Context, recipeID: String): List<Product> = runBlocking {
        val database = MainBD.getDb(context)
        return@runBlocking database.getRecipeDao().getRecipeProductsByRecipeID(recipeID) ?: listOf()
    }

    fun getProductByID(context: Context, productID: Int?): Product? = runBlocking {
        val database = MainBD.getDb(context)
        return@runBlocking productID?.let { database.getProductDao().getProductByID(it) }
    }

    fun getAllProducts(context: Context): List<Product> = runBlocking {
        val database = MainBD.getDb(context)
        return@runBlocking database.getProductDao().getAllProducts() ?: listOf()
    }

}