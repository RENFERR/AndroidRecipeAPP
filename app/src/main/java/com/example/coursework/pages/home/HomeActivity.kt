package com.example.coursework.pages.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.asLiveData
import com.example.coursework.R
import com.example.coursework.database.DatabaseUserMethods.getUserTypeName
import com.example.coursework.database.MainBD
import com.example.coursework.database.model.products.Product
import com.example.coursework.database.model.recipes.Recipe
import com.example.coursework.database.model.recipes.RecipeType
import com.example.coursework.databinding.ActivityHomePageBinding
import com.example.coursework.helpers.Constants.authorizedUser
import com.example.coursework.helpers.Constants.userTypeNameAdmin
import com.example.coursework.helpers.Constants.userTypeNameCreator
import com.example.coursework.helpers.Constants.userTypeNameUser
import com.example.coursework.helpers.Transitions
import com.example.coursework.pages.home.adapters.AdapterProducts
import com.example.coursework.pages.home.adapters.AdapterRecipe
import com.example.coursework.pages.home.adapters.AdapterRecipeType

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomePageBinding
    lateinit var actionBarToggle: ActionBarDrawerToggle
    private val transitions by lazy { Transitions(this@HomeActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigationMenu()
        setAdapters()
    }

    private fun setAdapters() {
        val database = MainBD.getDb(this@HomeActivity)
        database.getRecipeDao().getAllRecipeTypesFlow()?.asLiveData()?.observe(this@HomeActivity) { recipeCategoryList ->
            setAdapterCategory(recipeCategoryList)
        }
        database.getRecipeDao().getAllRecipesFlow()?.asLiveData()?.observe(this@HomeActivity) { recipesList ->
            setAdapterRecipes(recipesList)
        }
        database.getProductDao().getAllProductsFlow()?.asLiveData()?.observe(this@HomeActivity) { productList ->
            setAdapterProducts(productList)
        }
    }

    private fun setAdapterProducts(productList: List<Product>) {
        binding.rcProductRecipes.apply {
            this.adapter = AdapterProducts(productList)
        }
    }

    private fun setAdapterCategory(categoryList: List<RecipeType>) {
        binding.rcCategoryRecipes.apply {
            this.adapter = AdapterRecipeType(categoryList)
        }
    }

    private fun setAdapterRecipes(recipeList: List<Recipe>) {
        binding.rcPopularRecipes.apply {
            this.adapter = AdapterRecipe(recipeList)
        }
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

    override fun onBackPressed() {
        transitions.goToHome()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarToggle.onOptionsItemSelected(item)) return true
        return true
    }
}