package com.example.coursework.pages.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.asLiveData
import com.example.coursework.database.DatabaseProductsMethods.addNewProduct
import com.example.coursework.database.MainBD.Companion.getDb
import com.example.coursework.database.model.products.Product
import com.example.coursework.database.model.products.ProductType
import com.example.coursework.databinding.ActivityAdminProductsBinding
import com.example.coursework.pages.admin.adapters.AdapterSpinnerProductType

class ActivityAdminProducts : AppCompatActivity() {

    private lateinit var binding: ActivityAdminProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setProductTypesList()
        binding.buttonAddProduct.setOnClickListener { addProduct() }
    }

    private var pickedProductType: ProductType? = null
    private fun setProductTypesList() {
        val databaseDao = getDb(this@ActivityAdminProducts).getProductDao()
        databaseDao.getAllProductsTypesFlow()?.asLiveData()?.observe(this@ActivityAdminProducts) { productTypesList ->
            if (!productTypesList.isNullOrEmpty()) {
                AdapterSpinnerProductType(binding.spinnerProductType, productTypesList) { pickedProductType ->
                    this.pickedProductType = pickedProductType
                }.iniSpinner()
            }
        }
    }

    private fun addProduct() = with(binding) {
        val productName = inputEditTextProductName.text?.toString()
        val productType = pickedProductType?.productTypeID
        val productPictureURL = inputEditTextProductPictureUrl.text?.toString()
        val calories = inputEditTextProductCalories.text?.toString()
        val protein = inputEditTextProductProtein.text?.toString()
        val fat = inputEditTextProductFat.text?.toString()
        val carbohydrates = inputEditTextProductCarbohydrates.text?.toString()

        if (isAllFieldsValueIsNotNull(arrayListOf(productName, productType?.toString(), calories, protein, fat, carbohydrates))) {
            addNewProduct(this@ActivityAdminProducts, Product(
                null,
                productType!!,
                productName!!,
                productPictureURL,
                calories!!.toInt(),
                protein!!.toDouble(),
                fat!!.toDouble(),
                carbohydrates!!.toDouble())
            )
            inputEditTextProductName.text?.clear()
            inputEditTextProductPictureUrl.text?.clear()
            inputEditTextProductCalories.text?.clear()
            inputEditTextProductProtein.text?.clear()
            inputEditTextProductFat.text?.clear()
            inputEditTextProductCarbohydrates.text?.clear()
            Toast.makeText(
                this@ActivityAdminProducts,
                "Продукт $productName был добавлен",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this@ActivityAdminProducts,
                "Не все обязательные поля заполнены",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun isAllFieldsValueIsNotNull(valueList: ArrayList<String?>): Boolean {
        var isFieldsNotEmpty = true
        valueList.forEach {
            if (it.isNullOrEmpty()) {
                isFieldsNotEmpty = false
            }
        }
        return isFieldsNotEmpty
    }
}