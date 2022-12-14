package com.example.coursework.pages.admin.adapters

import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.coursework.database.model.products.ProductType

class AdapterSpinnerProductType(
    private val spinner: Spinner,
    private val productTypes: List<ProductType>,
    private val pickedProductType: (type: ProductType) -> Unit
): AdapterView.OnItemSelectedListener {

    private val contextView by lazy { spinner.context }

    fun iniSpinner() {
        val productTypesName = ArrayList<String>()
        productTypes.forEach {
            productTypesName.add(it.productTypeName)
        }
        val arrayAdapter = ArrayAdapter(contextView, android.R.layout.simple_spinner_item, productTypesName)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(spinner) {
            adapter = arrayAdapter
            setSelection(0, false)
            onItemSelectedListener = this@AdapterSpinnerProductType
            prompt = "Выберите тип продукта"
            gravity = Gravity.CENTER
        }
        pickedProductType(productTypes[0])
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        pickedProductType(productTypes[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d("Test", "AdapterSpinnerProductType -> onNothingSelected")
    }
}