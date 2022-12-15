package com.example.coursework.database.model.recipes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "recipe_step",
    foreignKeys = [ForeignKey(
        entity = Recipe::class,
        parentColumns = ["recipeID"],
        childColumns = ["recipeID"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RecipeStep(

    @PrimaryKey(autoGenerate = true            ) val recipeStepID          : Int? = null,
    @ColumnInfo(name = "recipeID"              ) var recipeID              : Int,
    @ColumnInfo(name = "recipeStepTitle"       ) var recipeStepTitle       : String,
    @ColumnInfo(name = "recipeStepDescription" ) var recipeStepDescription : String,
    @ColumnInfo(name = "recipeStepPictureURL"  ) var recipeStepPictureURL  : String?

)
