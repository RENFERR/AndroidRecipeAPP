package com.example.coursework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coursework.database.dao.ProductDao
import com.example.coursework.database.dao.RecipeDao
import com.example.coursework.database.dao.UserDao
import com.example.coursework.database.model.products.Product
import com.example.coursework.database.model.products.ProductType
import com.example.coursework.database.model.recipes.*
import com.example.coursework.database.model.users.User
import com.example.coursework.database.model.users.UserTypes
import com.example.coursework.helpers.Constants.userTypeNameAdmin
import com.example.coursework.helpers.Constants.userTypeNameCreator
import com.example.coursework.helpers.Constants.userTypeNameUser
import kotlinx.coroutines.runBlocking
import java.util.UUID

@Database(entities = [
    Product::class,
    ProductType::class,
    User::class,
    UserTypes::class,
    Recipe::class,
    RecipeType::class,
    RecipeStep::class,
    RecipeProducts::class], version = 1)
abstract class MainBD: RoomDatabase() {

    abstract fun getRecipeDao(): RecipeDao
    abstract fun getProductDao(): ProductDao
    abstract fun getUserDao(): UserDao

    companion object {
        fun getDb(context: Context): MainBD {
            val database = Room.databaseBuilder(
                context.applicationContext,
                MainBD::class.java,
                "main.db"
            ).build()
            addUserTypes(database)
            addAdminUser(database)

            addProductType(database)
            addProduct(database)

            addRecipeType(database)
            addRecipe(database)
            return database
        }

        private fun addRecipe(database: MainBD) {
            runBlocking {
                val recipeDao = database.getRecipeDao()

                val recipeID = UUID.randomUUID().toString()
                val recipe = Recipe(
                    recipeID,
                    1,
                    "Мясные шарики",
                    "https://grandkulinar.ru/uploads/posts/2018-05/1525548715_myasnye-shariki.jpg",
                    "Вкуные мясные шарики"
                )
                val recipeSteps = listOf(
                    RecipeStep(
                        null,
                        recipeID,
                        "Подготовка фарша",
                        "Берём мясо и пропускаем через мясорубку",
                        "https://cdn.setafi.com/wp/uploads/2019/05/image-1.jpg"
                    ),
                    RecipeStep(
                        null,
                        recipeID,
                        "Формируем шарики",
                        "Берём немного фарша и лепим шарик",
                        "https://www.gastronom.ru/binfiles/images/20200131/b44b105a.jpg"
                    ),
                    RecipeStep(
                        null,
                        recipeID,
                        "Жарим мясные шарики",
                        "Берём сковородку и жарим шарики на среднем огне",
                        "https://cdn.botanichka.ru/wp-content/uploads/2019/01/italyanskie-myasnyie-shariki-v-souse-s-kartofelnyim-pyure-14.jpg"
                    )
                )
                val recipeProduct = RecipeProducts(null, recipeID, 1, 350.0)
                val recipeList = recipeDao.getAllRecipes()
                if (recipeList.isNullOrEmpty()) {
                    recipeDao.addRecipe(recipe)
                    recipeDao.addRecipeProduct(recipeProduct)
                    for (step in recipeSteps) recipeDao.addRecipeStep(step)
                }
            }
        }
        private fun addRecipeType(database: MainBD) {
            runBlocking {
                val recipeDao = database.getRecipeDao()

                val recipeType = RecipeType(
                    1,
                    "Мясные изделия",
                    "Все мясные блюда",
                    "https://e2.edimdoma.ru/data/posts/0002/1819/21819-ed4_wide.jpg?1638865873"
                )
                val recipeTypeList = recipeDao.getAllRecipeTypes()
                if (recipeTypeList.isNullOrEmpty()) recipeDao.addRecipeType(recipeType)
            }
        }
        private fun addProduct(database: MainBD) {
            runBlocking {
                val productDao = database.getProductDao()

                val product = Product(1, 1, "Говядина", "https://halal-spb.ru/sites/default/files/meat-3359248_1920.jpg", 187, 18.9, 12.4, 0.0)
                val productList = productDao.getAllProducts()
                if (productList.isNullOrEmpty()) productDao.addProduct(product)
            }
        }
        private fun addProductType(database: MainBD) {
            runBlocking {
                val productDao = database.getProductDao()

                val productTypeList = productDao.getAllProductsTypes()
                val productType = ProductType(1, "Мясо", "Мясо и мясные изделия", "https://static.detstrana.ru/public/food/5b/65/09/952ff_8c96.jpg")
                if (productTypeList.isNullOrEmpty()) productDao.addProductType(productType)
            }
        }
        private fun addUserTypes(database: MainBD) {
            runBlocking {
                val userDao = database.getUserDao()

                val adminUserType = UserTypes(null, userTypeNameAdmin)
                val userUserType = UserTypes(null, userTypeNameUser)
                val creatorUserType = UserTypes(null, userTypeNameCreator)
                if (userDao.getUserTypeID(userTypeNameAdmin) == null) userDao.addUserType(
                    adminUserType
                )
                if (userDao.getUserTypeID(userTypeNameUser) == null) userDao.addUserType(
                    userUserType
                )
                if (userDao.getUserTypeID(userTypeNameCreator) == null) userDao.addUserType(
                    creatorUserType
                )
            }
        }
        private fun addAdminUser(database: MainBD) {
            runBlocking {
                val userDao = database.getUserDao()

                if (userDao.getUser("admin", "1234") == null) {
                    userDao.getUserTypeID(userTypeNameAdmin)?.let {
                        User(null, it, "admin", "1234")
                    }?.let {
                        userDao.addUser(it)
                    }
                }
            }
        }
    }
}