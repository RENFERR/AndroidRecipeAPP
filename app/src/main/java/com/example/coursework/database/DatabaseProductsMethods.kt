package com.example.coursework.database

import android.content.Context
import com.example.coursework.database.model.products.Product
import kotlinx.coroutines.runBlocking

object DatabaseProductsMethods {

    fun addNewProduct(context: Context, product: Product) = runBlocking {
        val database = MainBD.getDb(context)
        database.getProductDao().addProduct(product)
    }

}