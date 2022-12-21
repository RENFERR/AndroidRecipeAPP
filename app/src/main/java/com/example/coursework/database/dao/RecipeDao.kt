package com.example.coursework.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.coursework.database.model.products.Product
import com.example.coursework.database.model.recipes.Recipe
import com.example.coursework.database.model.recipes.RecipeProducts
import com.example.coursework.database.model.recipes.RecipeStep
import com.example.coursework.database.model.recipes.RecipeType
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert
    suspend fun addRecipe(recipe: Recipe)
    @Insert
    suspend fun addRecipeType(type: RecipeType)
    @Insert
    suspend fun addRecipeStep(step: RecipeStep)
    @Insert
    suspend fun addRecipeProduct(products: RecipeProducts)

    @Update
    suspend fun updateRecipe(recipe: Recipe)
    @Update
    suspend fun updateRecipeStep(recipeStep: RecipeStep)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)
    @Delete
    suspend fun deleteRecipeStep(step: RecipeStep)
    @Delete
    suspend fun deleteRecipeProduct(products: RecipeProducts)



    @Query("SELECT * FROM recipes")
    fun getAllRecipesFlow(): Flow<List<Recipe>>?
    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Recipe>?

    @Query("SELECT * FROM recipes WHERE recipeTypeID = :recipeTypeID")
    fun getRecipesWithType(recipeTypeID: Int): Flow<List<Recipe>?>

    @Query("SELECT * FROM recipe_types")
    fun getAllRecipeTypesFlow(): Flow<List<RecipeType>>?
    @Query("SELECT * FROM recipe_types")
    suspend fun getAllRecipeTypes(): List<RecipeType>?

    @Query("SELECT * FROM recipe_types WHERE recipeTypeID = :recipeTypeID")
    suspend fun getRecipeTypeWithID(recipeTypeID: Int): RecipeType?

    @Query("SELECT * FROM recipes WHERE recipeID = :recipeID")
    suspend fun getRecipeByID(recipeID: String): Recipe?

    @Query("SELECT * FROM recipe_step WHERE recipeStepID = :recipeStepID")
    suspend fun getRecipeStepByID(recipeStepID: String): RecipeStep?

    @Query("SELECT * FROM recipe_step WHERE recipeID = :recipeID")
    suspend fun getRecipeStepsByRecipeID(recipeID: String): List<RecipeStep>?

    @Query("SELECT * FROM recipes INNER JOIN recipe_products ON recipe_products.recipeID = recipes.recipeID INNER JOIN products ON products.productID = recipe_products.productID WHERE recipe_products.recipeID = :recipeID")
    suspend fun getProductsByRecipeID(recipeID: String): List<Product>?

    @Query("SELECT * FROM recipe_products WHERE recipeID = :recipeID")
    suspend fun getRecipeProductsByRecipeID(recipeID: String): List<RecipeProducts>?
}