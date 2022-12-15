package com.example.coursework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coursework.database.dao.ProductDao
import com.example.coursework.database.dao.RecipeDao
import com.example.coursework.database.dao.UserDao
import com.example.coursework.database.model.products.Product
import com.example.coursework.database.model.products.ProductType
import com.example.coursework.database.model.recipes.*
import com.example.coursework.database.model.users.User
import com.example.coursework.database.model.users.UserTypes
import com.example.coursework.helpers.Constants.userTypeNameAdmin
import com.example.coursework.helpers.Constants.userTypeNameCreator
import com.example.coursework.helpers.Constants.userTypeNameUser
import kotlinx.coroutines.runBlocking

@Database(entities = [
    Product::class,
    ProductType::class,
    User::class,
    UserTypes::class,
    Recipe::class,
    RecipeType::class,
    RecipeStep::class,
    RecipeProducts::class], version = 1)
abstract class MainBD: RoomDatabase() {

    abstract fun getRecipeDao(): RecipeDao
    abstract fun getProductDao(): ProductDao
    abstract fun getUserDao(): UserDao

    companion object {
        fun getDb(context: Context): MainBD {
            val database = Room.databaseBuilder(
                context.applicationContext,
                MainBD::class.java,
                "main.db"
            ).build()
            runBlocking {
                val adminUserType = UserTypes(null, userTypeNameAdmin)
                val userUserType = UserTypes(null, userTypeNameUser)
                val creatorUserType = UserTypes(null, userTypeNameCreator)

                if (database.getUserDao().getUserTypeID(userTypeNameAdmin) == null) database.getUserDao().addUserType(adminUserType)
                if (database.getUserDao().getUserTypeID(userTypeNameUser) == null) database.getUserDao().addUserType(userUserType)
                if (database.getUserDao().getUserTypeID(userTypeNameCreator) == null) database.getUserDao().addUserType(creatorUserType)

                if (database.getUserDao().getUser("admin", "1234") == null) {
                    database.getUserDao().getUserTypeID(userTypeNameAdmin)?.let {
                        User(null, it,"admin","1234")
                    }?.let {
                        database.getUserDao().addUser(it)
                    }
                }
            }
            return database
        }
    }
}