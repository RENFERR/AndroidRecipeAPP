package com.example.coursework.pages.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coursework.databinding.ActivityAdminRecipesCategoryBinding

class ActivityAdminRecipesCategory : AppCompatActivity() {

    private lateinit var binding: ActivityAdminRecipesCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminRecipesCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}