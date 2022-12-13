package com.example.coursework.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.coursework.database.model.products.Product
import com.example.coursework.database.model.users.User
import com.example.coursework.database.model.users.UserTypes
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    // Продукты
    @Insert suspend fun insertProduct(product: Product)
    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Product>>


    // Пользователи
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT login FROM users WHERE login = :insertedLogin")
    suspend fun isLoginExist(insertedLogin: String): String?

    @Query("SELECT * FROM users WHERE login = :insertedLogin AND password = :insertedPassword")
    suspend fun getUser(insertedLogin: String, insertedPassword: String): User?

    @Insert
    suspend fun insertUserType(userTypes: UserTypes)

    @Query("SELECT userTypeID FROM user_types WHERE type_name = :insertedTypeName")
    suspend fun getUserTypeID(insertedTypeName: String): Int?

    @Query("SELECT * FROM user_types")
    suspend fun getAllUserTypes(): List<UserTypes>?

    @Query("SELECT * FROM users")
    suspend fun getAllUser(): List<User>?

    @Delete
    suspend fun deleteUserType(userType: UserTypes)
}