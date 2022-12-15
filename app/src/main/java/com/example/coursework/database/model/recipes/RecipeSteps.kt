//package com.example.coursework.database.model.recipes
//
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.ForeignKey
//import androidx.room.PrimaryKey
//
//
//@Entity(
//    tableName = "recipe_steps",
//    foreignKeys = [ForeignKey(
//        entity = Recipe::class,
//        parentColumns = ["recipeID"],
//        childColumns = ["recipeID"],
//        onDelete = ForeignKey.CASCADE
//    ), ForeignKey(
//        entity = RecipeStep::class,
//        parentColumns = ["recipeStepID"],
//        childColumns = ["recipeStepID"],
//        onDelete = ForeignKey.CASCADE
//    )]
//)
//data class RecipeSteps(
//
//    @PrimaryKey(autoGenerate = true   ) val id            : Int,
//    @ColumnInfo(name = "recipeID"     ) val recipeID      : Int,
//    @ColumnInfo(name = "recipeStepID" ) val recipeStepID  : Int
//
//)
