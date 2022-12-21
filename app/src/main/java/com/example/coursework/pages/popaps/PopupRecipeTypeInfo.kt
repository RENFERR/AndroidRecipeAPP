package com.example.coursework.pages.popaps

import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import androidx.lifecycle.asLiveData
import com.example.coursework.database.DatabaseRecipeMethods.getRecipeByRecipeTypID
import com.example.coursework.database.MainBD
import com.example.coursework.database.model.recipes.Recipe
import com.example.coursework.databinding.PopupForItemsBinding
import com.example.coursework.pages.popaps.adapters.AdapterPopupRecipes
import android.animation.Animator
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager

class PopupRecipeTypeInfo: AppCompatActivity() {

    private lateinit var binding: PopupForItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        binding = PopupForItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val recipeTypeID = bundle?.getInt("recipeTypeID")
        Log.d("Test", "PopupRecipeTypeInfo -> onCreate: recipeTypeID = $recipeTypeID")
        if (recipeTypeID != null) {
            iniPopup(recipeTypeID)
        } else {
            Toast.makeText(
                this@PopupRecipeTypeInfo,
                "Произошла ошибка. Попробуйте позже",
                Toast.LENGTH_SHORT
            ).show()
        }

        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(this, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // If you want dark status bar, set darkStatusBar to true
                this.window.statusBarColor = Color.TRANSPARENT
                setWindowFlag(this, false)
            }
        }

        val alpha = 100 //between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            binding.popupWindowBackground.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimation.start()

        binding.popupCardView.alpha = 0f
        binding.popupCardView.animate().alpha(1f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

    }

    override fun onBackPressed() {
        val alpha = 100 // between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), alphaColor, Color.TRANSPARENT)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            binding.popupWindowBackground.setBackgroundColor(
                animator.animatedValue as Int
            )
        }

        // Fade animation for the Popup Window when you press the back button
        binding.popupCardView.animate().alpha(0f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        // After animation finish, close the Activity
        colorAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                finish()
                overridePendingTransition(0, 0)
            }
        })
        colorAnimation.start()
    }

    private fun setWindowFlag(activity: Activity, on: Boolean) {
        val win = activity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        } else {
            winParams.flags = winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        }
        win.attributes = winParams
    }

    private fun iniPopup(recipeTypeID: Int) {
        setTypeDescription(recipeTypeID)
        getRecipeList(recipeTypeID)
    }

    private fun setTypeDescription(recipeTypeID: Int) = with(binding) {
        val recipeType = getRecipeByRecipeTypID(this@PopupRecipeTypeInfo, recipeTypeID)
        popupTitle.text = "Рецепты по категориям"
        popupItemTitle.text = if (recipeType?.recipeTypeName.isNullOrEmpty()) "Не заголовка" else "Заголовок: ${recipeType?.recipeTypeName}"
        popupItemDescription.text = if (recipeType?.recipeTypeDescription.isNullOrEmpty()) "Нет описания" else "Описание: ${recipeType?.recipeTypeDescription}"
    }

    private fun getRecipeList(recipeTypeID: Int) {
        val database = MainBD.getDb(this@PopupRecipeTypeInfo)
        val flowRecipeList = database.getRecipeDao().getRecipesWithType(recipeTypeID)
        flowRecipeList.asLiveData().observe(this@PopupRecipeTypeInfo) { recipeList ->
            Log.d("Test", "PopupRecipeTypeInfo -> getRecipeList: $recipeList")
            if (recipeList.isNullOrEmpty()) {
                Toast.makeText(
                    this@PopupRecipeTypeInfo,
                    "Нет рецептов по данной категории",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                setRecyclerView(recipeList)
            }
        }
    }

    private fun setRecyclerView(recipeList: List<Recipe>) {
        binding.popupRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@PopupRecipeTypeInfo)
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
            this.adapter = AdapterPopupRecipes(recipeList)
        }
    }

}