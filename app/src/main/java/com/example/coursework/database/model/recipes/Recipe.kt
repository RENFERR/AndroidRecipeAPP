package com.example.coursework.database.model.recipes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "recipes",
    foreignKeys = [ForeignKey(
        entity = RecipeType::class,
        parentColumns = ["recipeTypeID"],
        childColumns = ["recipeTypeID"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Recipe(

    @PrimaryKey(autoGenerate = true        ) val recipeID          : Int? = null,
    @ColumnInfo(name = "recipeTypeID"      ) val recipeTypeID      : Int? = null,
    @ColumnInfo(name = "recipeName"        ) var recipeName        : String,
    @ColumnInfo(name = "recipePictureURL"  ) var recipePictureURL  : String,
    @ColumnInfo(name = "recipeDescription" ) var recipeDescription : String,
    @ColumnInfo(name = "recipeIngredients" ) var recipeIngredients : Int

)
