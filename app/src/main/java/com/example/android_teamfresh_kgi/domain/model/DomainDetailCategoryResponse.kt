package com.example.android_teamfresh_kgi.domain.model


data class DomainDetailCategoryResponse(
    val data: DomainDispClasData,
    val message: String?
)


data class DomainAppSubDispClasInfo(
    val dispClasSeq: Int,
    val subDispClasNm: String,
    val prntsDispClasSeq: Int,
    val dispClasCd: String,
    val dispClasLvl: String
)

data class DomainDispClasData(
    val dispClasNm: String,
    val appSubDispClasInfoList: List<DomainAppSubDispClasInfo>
)