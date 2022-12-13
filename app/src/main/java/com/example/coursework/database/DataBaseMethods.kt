package com.example.coursework.database

import android.content.Context
import com.example.coursework.database.model.users.User
import com.example.coursework.database.model.users.UserTypes
import com.example.coursework.helpers.Constants
import kotlinx.coroutines.runBlocking

object DatabaseMethods {

    fun insertUserToDB(
        context: Context, userLogin: String, userPassword: String, isAuthor: Boolean
    ): Boolean = runBlocking {
        val database = MainBD.getDb(context)
        val selectedLogin = database.getDao().isLoginExist(userLogin)

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
            runBlocking { user?.let { database.getDao().insertUser(it) } }
            true
        }
    }

    fun getUserFromDB(
        context: Context, userLogin: String, userPassword: String
    ): User? = runBlocking {
        val database = MainBD.getDb(context)
        val selectedUser = database.getDao().getUser(insertedLogin = userLogin, insertedPassword = userPassword)
        return@runBlocking selectedUser
    }

    fun getUserTypeID(context: Context, userTypeName: String): Int? = runBlocking {
        val database = MainBD.getDb(context)
        return@runBlocking database.getDao().getUserTypeID(userTypeName)//?.get(0)
    }

    fun insertUserType(context: Context, userType: UserTypes) = runBlocking {
        val database = MainBD.getDb(context)
        database.getDao().insertUserType(userType)
    }

    fun deleteUserType(context: Context, userType: UserTypes) = runBlocking {
        val database = MainBD.getDb(context)
        database.getDao().deleteUserType(userType)
    }

}