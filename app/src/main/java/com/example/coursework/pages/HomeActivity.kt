package com.example.coursework.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.coursework.R
import com.example.coursework.database.DatabaseUserMethods.getUserTypeName
import com.example.coursework.databinding.ActivityHomePageBinding
import com.example.coursework.helpers.Constants.authorizedUser
import com.example.coursework.helpers.Constants.userTypeNameAdmin
import com.example.coursework.helpers.Constants.userTypeNameCreator
import com.example.coursework.helpers.Constants.userTypeNameUser
import com.example.coursework.helpers.Transitions

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomePageBinding
    lateinit var actionBarToggle: ActionBarDrawerToggle
    private val transitions by lazy { Transitions(this@HomeActivity) }

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


        val adminPanel = navViewHomePage.menu.findItem(R.id.admin_panel)
        when(authorizedUser?.userTypeID?.let { getUserTypeName(this@HomeActivity, it) }) {
            userTypeNameUser -> {
                adminPanel.isVisible = false
                navViewHomePage.menu.findItem(R.id.recipes_add).isVisible = false
            }
            userTypeNameCreator -> adminPanel.isVisible = false
            userTypeNameAdmin -> adminPanel.isVisible = true
        }

        navViewHomePage.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.acc_user_profile -> {
                    transitions.goToUserProfile()
                    true
                }
                R.id.recipes_list -> {
                    transitions.goToRecipesList()
                    true
                }
                R.id.recipes_add -> {
                    transitions.goToAddRecipe()
                    true
                }
                R.id.products_list -> {
                    transitions.goToProductsList()
                    true
                }
                R.id.admin_edit_product -> {
                    transitions.goToAdminProducts()
                    true
                }
                R.id.admin_edit_product_category -> {
                    transitions.goToAdminProductsCategory()
                    true
                }
                R.id.admin_edit_recipe_category -> {
                    transitions.goToAdminRecipesCategory()
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