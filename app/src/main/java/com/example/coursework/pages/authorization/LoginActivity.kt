package com.example.coursework.pages.authorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.coursework.database.DatabaseUserMethods.getUserFromDB
import com.example.coursework.databinding.ActivityLoginBinding
import com.example.coursework.helpers.Constants.authorizedUser
import com.example.coursework.helpers.Transitions

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val transitions by lazy { Transitions(this@LoginActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        deleteDatabase("main.db")
        binding.tvRegistration.setOnClickListener { transitions.goToRegistrationForm() }
        binding.btnLogin.setOnClickListener { authorization() }
    }

    private fun authorization() = with(binding) {
        val login = tieLogin.text.toString()
        val password = tiePass.text.toString()

        val user = getUserFromDB(
            context = this@LoginActivity,
            userLogin = login,
            userPassword = password
        )

        if (user != null) {
            authorizedUser = user
            transitions.goToHomePage()
        } else {
            Toast.makeText(this@LoginActivity, "Не верный логин или пароль", Toast.LENGTH_SHORT)
                .show()
            // TODO: Сообщение о том, что логин или пароль не верный
        }
    }

}