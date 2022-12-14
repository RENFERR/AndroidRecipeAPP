package com.example.coursework.database

import android.content.Context
import com.example.coursework.database.model.users.User
import com.example.coursework.database.model.users.UserTypes
import com.example.coursework.helpers.Constants
import kotlinx.coroutines.runBlocking

object DatabaseUserMethods {

    fun insertUserToDB(
        context: Context, userLogin: String, userPassword: String, isAuthor: Boolean
    ): Boolean = runBlocking {
        val database = MainBD.getDb(context)
        val selectedLogin = database.getUserDao().isLoginExist(userLogin)

        return@runBlocking if (!selectedLogin.isNullOrEmpty()) {
            false
        } else {
            val userTypeID: Int? = if (isAuthor) {
                getUserTypeID(context, Constants.userTypeNameCreator)
            } else {
                getUserTypeID(context, Constants.userTypeNameUser)
            }
            val user = userTypeID?.let {
                User(
                    null,
                    it,
                    userLogin,
                    userPassword
                )
            }
            runBlocking { user?.let { database.getUserDao().addUser(it) } }
            true
        }
    }

    fun getUserFromDB(
        context: Context, userLogin: String, userPassword: String
    ): User? = runBlocking {
        val database = MainBD.getDb(context)
        return@runBlocking database.getUserDao()
            .getUser(insertedLogin = userLogin, insertedPassword = userPassword)
    }

    fun getUserTypeID(context: Context, userTypeName: String): Int? = runBlocking {
        val database = MainBD.getDb(context)
        return@runBlocking database.getUserDao().getUserTypeID(userTypeName)//?.get(0)
    }

    fun getUserTypeName(context: Context, userTypeID: Int): String? = runBlocking {
        val database = MainBD.getDb(context)
        return@runBlocking database.getUserDao().getUserTypeName(userTypeID)
    }

    fun insertUserType(context: Context, userType: UserTypes) = runBlocking {
        val database = MainBD.getDb(context)
        database.getUserDao().addUserType(userType)
    }

    fun deleteUserType(context: Context, userType: UserTypes) = runBlocking {
        val database = MainBD.getDb(context)
        database.getUserDao().deleteUserType(userType)
    }

}