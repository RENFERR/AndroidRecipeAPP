package com.example.coursework.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coursework.database.model.products.Product
import com.example.coursework.database.model.products.ProductType
import com.example.coursework.database.model.users.User
import com.example.coursework.database.model.users.UserTypes
import com.example.coursework.helpers.Constants.userTypeNameAdmin
import com.example.coursework.helpers.Constants.userTypeNameCreator
import com.example.coursework.helpers.Constants.userTypeNameUser
import kotlinx.coroutines.runBlocking

@Database(entities = [Product::class, ProductType::class, User::class, UserTypes::class], version = 1)
abstract class MainBD: RoomDatabase() {

    abstract fun getDao(): Dao

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
                if (database.getDao().getUserTypeID(userTypeNameAdmin) == null) database.getDao().insertUserType(adminUserType)
                if (database.getDao().getUserTypeID(userTypeNameUser) == null) database.getDao().insertUserType(userUserType)
                if (database.getDao().getUserTypeID(userTypeNameCreator) == null) database.getDao().insertUserType(creatorUserType)
            }
            return database
        }
    }
}