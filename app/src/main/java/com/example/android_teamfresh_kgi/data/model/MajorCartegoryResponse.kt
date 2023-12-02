package com.example.android_teamfresh_kgi.data.model
data class MajorCategoryResponse(
    val data: List<MajorDispClasItem>,
    val message: String?
)

data class MajorDispClasItem(
    val dispClasSeq: Int,
    val dispClasNm: String,
    val dispClasImgPath: String,
    val dispClasCd: String
)