package com.example.coursework.pages.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coursework.databinding.ActivityRecipesListBinding

class ActivityRecipesList : AppCompatActivity() {

    private lateinit var binding: ActivityRecipesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}