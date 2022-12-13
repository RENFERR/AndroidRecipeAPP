package com.example.coursework.pages.authorization

import android.content.Context
import com.example.coursework.database.MainBD
import kotlinx.coroutines.runBlocking


data class ValidationMessage(
    val isValidate: Boolean,
    val errorMessage: String?
)

object Validation {

    fun isPasswordValidate(password: String): ValidationMessage {
        return if (password.count() == 4) {
            ValidationMessage(true, null)
        } else {
            ValidationMessage(false, "Не допустимая длинна пароля")
        }
    }

    fun isLoginValidateToRegister(login: String, context: Context): ValidationMessage {
        return if (login.count() >= 3) {
            val dataBase = MainBD.getDb(context)
            val isLoginExist = runBlocking {
                !dataBase.getDao().isLoginExist(login).isNullOrEmpty()
            }
            if (isLoginExist) {
                ValidationMessage(false, "Такой логин уже существует")
            } else {
                ValidationMessage(true, null)
            }
        } else {
            ValidationMessage(false, "Логин должен содержать больше  3-х символов")
        }
    }

}