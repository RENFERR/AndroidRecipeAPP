package com.example.coursework.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.coursework.R
import com.example.coursework.databinding.ActivityHomePageBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomePageBinding
    lateinit var actionBarToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigationMenu()
    }

    private fun setNavigationMenu() = with(binding) {
        actionBarToggle = ActionBarDrawerToggle(this@HomeActivity, drawerHomePage, 0 , 0)
        drawerHomePage.addDrawerListener(actionBarToggle)
        actionBarToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navViewHomePage.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.acc_user_profile -> {
                    true
                }
                R.id.acc_user_settings -> {
                    true
                }
                R.id.recipes_search -> {
                    true
                }
                R.id.recipes_category -> {
                    true
                }
                R.id.recipes_add -> {
                    true
                }
                R.id.products_search -> {
                    true
                }
                R.id.products_category -> {
                    true
                }
                R.id.products_category -> {
                    true
                }
                R.id.admin_edit_product -> {
                    true
                }
                R.id.admin_edit_product_category -> {
                    true
                }
                R.id.admin_edit_recipe_category -> {
                    true
                }
                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarToggle.onOptionsItemSelected(item)) return true
        return true
    }
}