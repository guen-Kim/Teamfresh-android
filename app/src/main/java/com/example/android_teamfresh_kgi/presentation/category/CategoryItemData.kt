package com.example.android_teamfresh_kgi.presentation.category


sealed class CategoryItemData

data class CategoryMenu(
    val name: String,
    val image: String,
    val type : MenuType,
) : CategoryItemData()

data class CategoryTitle(
    val title: String,
) : CategoryItemData()

data class CategoryBar(val bar: String) : CategoryItemData()

enum class MenuType{
    NOMAL,
    QUICK,
}

