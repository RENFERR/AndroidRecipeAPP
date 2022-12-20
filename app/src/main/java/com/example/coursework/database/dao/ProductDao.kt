package com.example.coursework.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.coursework.database.model.products.Product
import com.example.coursework.database.model.products.ProductType
import com.example.coursework.database.model.recipes.RecipeProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert
    suspend fun addProduct(product: Product)
    @Insert
    suspend fun addRecipeProduct(recipeProducts: RecipeProducts)
    @Insert
    suspend fun addProductType(productType: ProductType)

    @Delete
    suspend fun deleteProduct(product: Product)
    @Delete
    suspend fun deleteProductType(productType: ProductType)

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Product>?
    @Query("SELECT * FROM product_types")
    fun getAllProductsTypes(): Flow<List<ProductType>>?
    @Query("SELECT * FROM products WHERE productTypeID = :productTypeID")
    fun getProductsWithType(productTypeID: Int): Flow<List<Product>>?
    @Query("SELECT * FROM products WHERE productID = :productID")
    suspend fun getProductByID(productID: Int): Product?

}