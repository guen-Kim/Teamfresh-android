package com.example.android_teamfresh_kgi.presentation.home

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android_teamfresh_kgi.R
import com.example.android_teamfresh_kgi.databinding.ActivityMainBinding
import com.example.android_teamfresh_kgi.presentation.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun init() {


        // NavController 설정
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_main)

        // app themes 색상 참조 X
        bottomNavigationView.itemIconTintList = null

        val navController =
            supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()
        navController?.let {
            bottomNavigationView.setupWithNavController(it)
        }

    }



}