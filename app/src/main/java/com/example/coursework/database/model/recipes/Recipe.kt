package com.example.coursework.database.model.recipes

import androidx.room.Entity


@Entity(tableName = "recipes")
data class Recipe(

    val recipeID: Int? = null,
    val name: String,
    val description: String,
    val ingredients: Int

)
