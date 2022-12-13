package com.example.coursework.helpers

import com.example.coursework.database.model.users.User

object Constants {

    // Авторизованный пользователь
    var authorizedUser: User? = null

    // User types
    const val userTypeNameAdmin: String = "USER_TYPE_ADMIN"
    const val userTypeNameUser: String = "USER_TYPE_USER"
    const val userTypeNameCreator: String = "USER_TYPE_CREATOR"

}