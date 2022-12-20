package com.example.coursework.pages.recipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coursework.R
import com.example.coursework.database.DatabaseProductsMethods.getAllProducts
import com.example.coursework.database.DatabaseProductsMethods.getProductByID
import com.example.coursework.database.DatabaseProductsMethods.getRecipeProductsByRecipeID
import com.example.coursework.database.model.recipes.RecipeProducts
import com.example.coursework.databinding.ItemAddedProductInRecipeBinding
import com.example.coursework.helpers.SimpleTextWatcher

class AdapterAddRecipeProduct: RecyclerView.Adapter<AdapterAddRecipeProduct.ViewHolder>() {

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = ItemAddedProductInRecipeBinding.bind(item)
        private val contextView by lazy { item.context }

        fun bind(recipeProduct: RecipeProducts) = with(binding) {
            inputEditTextRecipeAddProductWeight.setText(recipeProduct.productCount.toString())

            val iniProduct = if (recipeProduct.productID != null) getProductByID(contextView, recipeProduct.productID) else null

            val productsList = getAllProducts(contextView)
            productsList.isNotEmpty().let {
                AdapterSpinnerProducts(spinnerProductsList, productsList) { pickedProduct ->
                    recipeProduct.productID = pickedProduct.productID
                }.iniSpinner(iniProduct)
            }

            inputEditTextRecipeAddProductWeight.addTextChangedListener(object : SimpleTextWatcher() {
                override fun onTextChanged(field: CharSequence?, start: Int, before: Int, count: Int) {
                    val valueOfField = field?.toString()
                    if (!valueOfField.isNullOrEmpty()) {
                        recipeProduct.productCount = valueOfField.toDouble()
                    } else {
                        recipeProduct.productCount = 0.0
                    }
                }
            })

            buttonDeleteProductFromRecipe.setOnClickListener {
                AdapterAddRecipeProduct.productsList.remove(recipeProduct)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_added_product_in_recipe, viewGroup, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productsList[position])
    }

    override fun getItemCount(): Int = productsList.size

    fun addRecipeProduct(iniRecipeProduct: RecipeProducts) {
        productsList.add(iniRecipeProduct)
        notifyItemInserted(productsList.count())
    }

    companion object {
        val productsList = ArrayList<RecipeProducts>()
    }
}