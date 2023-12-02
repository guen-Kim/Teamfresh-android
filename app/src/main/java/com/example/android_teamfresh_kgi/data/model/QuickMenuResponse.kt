package com.example.android_teamfresh_kgi.data.model

data class QuickMenuResponse(
    val data: List<DispClasItem>,
    val message: String?
)



data class DispClasItem(
    val quickMenuSeq: Int,
    val quickMenuNm: String,
    val quickMenuImgPath: String,
    val quickMenuConcScrenNm: String,
    val quickMenuConcScrenIden: String?,
    val quickMenuMovScrenPath: String?,
    )