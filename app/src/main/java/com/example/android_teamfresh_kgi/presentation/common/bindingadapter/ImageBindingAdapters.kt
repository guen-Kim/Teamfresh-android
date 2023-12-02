package com.example.android_teamfresh_kgi.presentation.common.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {

    if(!imageUrl.isNullOrEmpty()){
        Glide.with(view)
            .load("https://d1afu5va4iy6dc.cloudfront.net/$imageUrl")
            .into(view)
    }
}
