package com.example.coursework.pages.recipes.adapters

import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.coursework.R
import com.example.coursework.database.model.products.Product

class AdapterSpinnerProducts(
    private val spinner: Spinner,
    private val products: List<Product>,
    private val pickedProduct: (product: Product) -> Unit
): AdapterView.OnItemSelectedListener {

    private val contextView by lazy { spinner.context }

    fun iniSpinner(iniProduct: Product?) {
        val productsName = ArrayList<String>()
        products.forEach {
            productsName.add(it.name)
        }
        val arrayAdapter = ArrayAdapter(contextView, android.R.layout.simple_spinner_item, productsName)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(spinner) {
            adapter = arrayAdapter
            setSelection(0, false)
            onItemSelectedListener = this@AdapterSpinnerProducts
            prompt = "Выберите тип продукта"
            gravity = Gravity.CENTER
        }
        if (iniProduct != null) pickedProduct(iniProduct) else pickedProduct(products[0])
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        pickedProduct(products[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}