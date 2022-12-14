package com.example.coursework.database.model.recipes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "recipe_types")
data class RecipeType(

    @PrimaryKey(autoGenerate = true            ) val recipeTypeID          : Int? = null,
    @ColumnInfo(name = "recipeTypeName"        ) var recipeTypeName        : String,
    @ColumnInfo(name = "recipeTypeDescription" ) var recipeTypeDescription : String,
    @ColumnInfo(name = "recipeTypePictureURL"  ) var recipeTypePictureURL  : String

)
