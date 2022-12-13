package com.example.coursework.pages.authorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.coursework.database.DatabaseMethods.insertUserToDB
import com.example.coursework.databinding.ActivityRegistrationBinding
import com.example.coursework.helpers.Transitions
import com.example.coursework.pages.authorization.Validation.isLoginValidateToRegister
import com.example.coursework.pages.authorization.Validation.isPasswordValidate

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private val transitions by lazy { Transitions(this@RegistrationActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistration.setOnClickListener {
            fieldValidationCheck()
        }
    }

    private fun fieldValidationCheck() = with(binding) {
        val login = tieRegistrationLogin.text.toString()
        val password = tieRegistrationPass.text.toString()

        val loginValidate = isLoginValidateToRegister(login, this@RegistrationActivity)
        val passwordValidate = isPasswordValidate(password)

        tilRegistrationLogin.error = loginValidate.errorMessage
        tilRegistrationPass.error = passwordValidate.errorMessage

        if (loginValidate.isValidate) {
            tilRegistrationLogin.isErrorEnabled = false
            if (passwordValidate.isValidate) {
                tilRegistrationPass.isErrorEnabled = false
                val isUserAddToDatabaseSuccess = insertUserToDB(
                    this@RegistrationActivity,
                    userLogin = login,
                    userPassword = password,
                    isAuthor = binding.isAuthor.isChecked,
                )
                if (isUserAddToDatabaseSuccess) {
                    transitions.goToLoginForm()
                } else {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Введённый логин уже занят",
                        Toast.LENGTH_SHORT
                    ).show()
                    // TODO: Сообщение о том, что такой логин уже есть
                }
            } else {
                tilRegistrationPass.isErrorEnabled = true
            }
        } else {
            tilRegistrationLogin.isErrorEnabled = true
        }
    }




}