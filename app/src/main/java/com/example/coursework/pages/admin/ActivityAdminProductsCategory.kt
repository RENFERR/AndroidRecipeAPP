package com.example.coursework.pages.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.coursework.database.MainBD.Companion.getDb
import com.example.coursework.database.model.products.ProductType
import com.example.coursework.databinding.ActivityAdminProductsCategoryBinding
import kotlinx.coroutines.runBlocking

class ActivityAdminProductsCategory : AppCompatActivity() {

    private lateinit var binding: ActivityAdminProductsCategoryBinding
    private val databaseProductDao by lazy { getDb(this@ActivityAdminProductsCategory).getProductDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminProductsCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.buttonAddProductCategory.setOnClickListener { addProductCategory() }
    }

    private fun addProductCategory() = runBlocking {
        with(binding) {
            inputLayoutProductCategoryName.error = "Это обязательное поле"

            val productTypeName = inputEditTextProductTypeName.text?.toString()
            val productTypeDescription = inputEditTextProductTypeDescription.text?.toString()
            val productTypePictureUrl = inputEditTextProductCategoryPictureUrl.text?.toString()

            if (!productTypeName.isNullOrEmpty()) {
                inputLayoutProductCategoryName.isErrorEnabled = false
                val productType = ProductType(null, productTypeName, productTypeDescription, productTypePictureUrl)
                databaseProductDao.addProductType(productType)

                inputEditTextProductTypeName.text?.clear()
                inputEditTextProductTypeDescription.text?.clear()
                inputEditTextProductCategoryPictureUrl.text?.clear()

                Toast.makeText(
                    this@ActivityAdminProductsCategory,
                    "Категороия ${productType.productTypeName} была добавлена",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                inputLayoutProductCategoryName.isErrorEnabled = true
            }
        }
    }


}