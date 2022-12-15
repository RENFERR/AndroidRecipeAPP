package com.example.coursework.pages.recipes.adapters

import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.coursework.database.model.recipes.RecipeType

class AdapterSpinnerRecipeTypes(
    private val spinner: Spinner, private val typesList: List<RecipeType>, private val pickedType: (RecipeType) -> Unit
) : AdapterView.OnItemSelectedListener {

    private val contextView by lazy { spinner.context }

    fun iniSpinner() {
        val productTypesName = ArrayList<String>()
        typesList.forEach {
            productTypesName.add(it.recipeTypeName)
        }
        val arrayAdapter = ArrayAdapter(contextView, android.R.layout.simple_spinner_item, productTypesName)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(spinner) {
            adapter = arrayAdapter
            setSelection(0, false)
            onItemSelectedListener = this@AdapterSpinnerRecipeTypes
            prompt = "Выберите категорию рецепта"
            gravity = Gravity.CENTER
        }
        if (typesList.isNotEmpty()) pickedType(typesList[0])
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        pickedType(typesList[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d("Test", "AdapterSpinnerRecipeTypes -> onNothingSelected")
    }
}