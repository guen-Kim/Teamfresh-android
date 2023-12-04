package com.example.android_teamfresh_kgi.data.model


data class DetailCategoryResponse(
    val data: DispClasData,
    val message: String?
)


data class AppSubDispClasInfo(
    val dispClasSeq: Int,
    val subDispClasNm: String,
    val prntsDispClasSeq: Int,
    val dispClasCd: String,
    val dispClasLvl: String
)

data class DispClasData(
    val dispClasNm: String,
    val appSubDispClasInfoList: List<AppSubDispClasInfo>
)