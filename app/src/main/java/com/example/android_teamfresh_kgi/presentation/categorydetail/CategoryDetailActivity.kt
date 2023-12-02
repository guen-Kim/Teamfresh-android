package com.example.android_teamfresh_kgi.presentation.categorydetail

import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.android_teamfresh_kgi.R
import com.example.android_teamfresh_kgi.databinding.ActivityCategoryDetailBinding
import com.example.android_teamfresh_kgi.presentation.base.BaseActivity

class CategoryDetailActivity : BaseActivity<ActivityCategoryDetailBinding>(R.layout.activity_category_detail) {
    override fun init() {
        (this as AppCompatActivity).setSupportActionBar(binding.toolbar)


        val intent = intent
        val sqe = intent.getIntExtra("KEY_CATEGORY_SEQENCE",0)

        shortShowToast(sqe.toString())

    }
}