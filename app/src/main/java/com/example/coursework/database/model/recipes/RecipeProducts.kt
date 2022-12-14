package com.example.coursework.database.model.recipes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.coursework.database.model.products.Product


@Entity(
    tableName = "recipe_products",
    foreignKeys = [ForeignKey(
        entity = Recipe::class,
        parentColumns = ["recipeID"],
        childColumns = ["recipeID"],
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = Product::class,
        parentColumns = ["productID"],
        childColumns = ["productID"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RecipeProducts(

    @PrimaryKey(autoGenerate = true   ) val id               : Int,
    @ColumnInfo(name = "recipeID"     ) val recipeID         : Int,
    @ColumnInfo(name = "productID"    ) val productID        : Int,
    @ColumnInfo(name = "productCount" ) val productCount     : Double

)
