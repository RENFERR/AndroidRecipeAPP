package com.example.coursework.helpers

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.coursework.R

object InsertPictureIntoImageView {

    fun ImageView.setImage(url: String) {
        val contextView: Context = this.context
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .centerCrop()
            .placeholder(contextView.getDrawable(R.drawable.ic_downloading))
            .error(contextView.getDrawable(R.drawable.ic_error))
            .into(this)
    }

}