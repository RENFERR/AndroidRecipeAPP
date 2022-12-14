package com.example.coursework.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.coursework.database.model.users.User
import com.example.coursework.database.model.users.UserTypes

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)
    @Insert
    suspend fun addUserType(userTypes: UserTypes)

    @Delete
    suspend fun deleteUserType(userType: UserTypes)
    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT login FROM users WHERE login = :insertedLogin")
    suspend fun isLoginExist(insertedLogin: String): String?
    @Query("SELECT * FROM users WHERE login = :insertedLogin AND password = :insertedPassword")
    suspend fun getUser(insertedLogin: String, insertedPassword: String): User?
    @Query("SELECT userTypeID FROM user_types WHERE type_name = :insertedTypeName")
    suspend fun getUserTypeID(insertedTypeName: String): Int?
    @Query("SELECT type_name FROM user_types WHERE userTypeID = :userTypeID")
    suspend fun getUserTypeName(userTypeID: Int): String?
    @Query("SELECT * FROM user_types")
    suspend fun getAllUserTypes(): List<UserTypes>?
    @Query("SELECT * FROM users")
    suspend fun getAllUser(): List<User>?

}