package com.example.android_teamfresh_kgi.domain.model

data class DomainMajorCategoryResponse(
    val data: List<DomainMajorDispClasItem>,
    val message: String?
)

data class DomainMajorDispClasItem(
    val dispClasSeq: Int,
    val dispClasNm: String,
    val dispClasImgPath: String,
    val dispClasCd: String
)