package com.example.android_teamfresh_kgi.presentation.model


sealed class CategoryItemData

data class CategoryMenu(
    val dispClasSeq: Int,
    val dispClasNm: String,
    val dispClasImgPath: String,
    val dispClasCd: String,
) : CategoryItemData()


data class QuickMenu(
    val quickMenuSeq: Int,
    val quickMenuNm: String,
    val quickMenuImgPath: String,
    val quickMenuConcScrenNm: String,
    val quickMenuConcScrenIden: String?,
    val quickMenuMovScrenPath: String?,
    ) : CategoryItemData()

data class CategoryTitle(
    val title: String,
) : CategoryItemData()

object CategoryBar : CategoryItemData()


