package com.example.android_teamfresh_kgi.domain.model

data class DomainQuickMenuResponse(
    val data: List<DomainDispClasItem>,
    val message: String?,
)
data class DomainDispClasItem(
    val quickMenuSeq: Int,
    val quickMenuNm: String,
    val quickMenuImgPath: String,
    val quickMenuConcScrenNm: String,
    val quickMenuConcScrenIden: String?,
    val quickMenuMovScrenPath: String?,
)