package com.example.coursework.helpers

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.coursework.pages.HomeActivity
import com.example.coursework.pages.authorization.LoginActivity
import com.example.coursework.pages.authorization.RegistrationActivity

class Transitions(private val context: Context) {

    fun goToRegistrationForm() {
        val intent = Intent(context, RegistrationActivity::class.java)
        ContextCompat.startActivity(context, intent, null)
    }

    fun goToLoginForm() {
        val intent = Intent(context, LoginActivity::class.java)
        ContextCompat.startActivity(context, intent, null)
    }

    fun goToHomePage() {
        val intent = Intent(context, HomeActivity::class.java)
        ContextCompat.startActivity(context, intent, null)
    }

}