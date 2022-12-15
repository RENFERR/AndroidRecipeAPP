package com.example.coursework.pages.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        databaseDao.getAllProductsTypes()?.asLiveData()?.observe(this@ActivityAdminProducts) { productTypesList ->
            AdapterSpinnerProductType(binding.spinnerProductType, productTypesList) { pickedProductType ->
                this.pickedProductType = pickedProductType
                Log.d("Test", "ActivityAdminProducts -> setProductTypesList: picked type = $pickedProductType")
            }.iniSpinner()
        }
    }

    private fun addProduct() = with(binding) {
        val productName = inputEditTextProductName.text?.toString()
        val productType = pickedProductType?.productTypeID
        val productPictureURL = inputEditTextProductPictureUrl.text?.toString()
        val calories = inputEditTextProductCalories.text?.toString()?.toInt()
        val protein = inputEditTextProductProtein.text?.toString()?.toDouble()
        val fat = inputEditTextProductFat.text?.toString()?.toDouble()
        val carbohydrates = inputEditTextProductCarbohydrates.text?.toString()?.toDouble()

        if (isAllFieldsValueIsNotNull(arrayListOf(productName, productType, calories, protein, fat, carbohydrates))) {
            addNewProduct(this@ActivityAdminProducts, Product(
                null,
                productType!!,
                productName!!,
                productPictureURL,
                calories!!,
                protein!!,
                fat!!,
                carbohydrates!!)
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

    private fun isAllFieldsValueIsNotNull(valueList: ArrayList<Any?>): Boolean {
        var isFieldsNotEmpty = false
        valueList.forEach {
            if (it?.toString()?.isEmpty() != true) {
                isFieldsNotEmpty = true
            }
        }
        return isFieldsNotEmpty
    }
}