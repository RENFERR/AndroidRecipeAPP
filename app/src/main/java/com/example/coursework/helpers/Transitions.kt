package com.example.coursework.helpers

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.coursework.pages.home.HomeActivity
import com.example.coursework.pages.admin.ActivityAdminProducts
import com.example.coursework.pages.admin.ActivityAdminProductsCategory
import com.example.coursework.pages.admin.ActivityAdminRecipesCategory
import com.example.coursework.pages.authorization.LoginActivity
import com.example.coursework.pages.authorization.RegistrationActivity
import com.example.coursework.pages.products.ActivityProductsList
import com.example.coursework.pages.recipes.ActivityAddRecipe
import com.example.coursework.pages.recipes.ActivityRecipeInfo
import com.example.coursework.pages.recipes.ActivityRecipesList

class Transitions(private val context: Context) {

    fun goToHome() {
        val toHomeIntent = Intent(Intent.ACTION_MAIN)
        toHomeIntent.addCategory(Intent.CATEGORY_HOME)
        toHomeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(context, toHomeIntent, null)
    }

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

    fun goToRecipesList() {
        val intent = Intent(context, ActivityRecipesList::class.java)
        ContextCompat.startActivity(context, intent, null)
    }

    fun goToAddRecipe() {
        val intent = Intent(context, ActivityAddRecipe::class.java)
        ContextCompat.startActivity(context, intent, null)
    }

    fun goToProductsList() {
        val intent = Intent(context, ActivityProductsList::class.java)
        ContextCompat.startActivity(context, intent, null)
    }

    fun goToAdminProducts() {
        val intent = Intent(context, ActivityAdminProducts::class.java)
        ContextCompat.startActivity(context, intent, null)
    }

    fun goToAdminProductsCategory() {
        val intent = Intent(context, ActivityAdminProductsCategory::class.java)
        ContextCompat.startActivity(context, intent, null)
    }

    fun goToAdminRecipesCategory() {
        val intent = Intent(context, ActivityAdminRecipesCategory::class.java)
        ContextCompat.startActivity(context, intent, null)
    }

    fun goToRecipeInfo(recipeID: String) {
        val intent = Intent(context, ActivityRecipeInfo::class.java)
        intent.putExtra("recipeID", recipeID)
        ContextCompat.startActivity(context, intent, null)
    }

}