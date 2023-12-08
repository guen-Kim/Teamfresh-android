package com.example.android_teamfresh_kgi.data.model

import com.example.android_teamfresh_kgi.domain.model.DomainProduct
import com.google.gson.annotations.SerializedName


data class ProductPagingInfoResponse(
    @SerializedName("data") val productList: List<Product>,
    val pagination: Pagination
)


data class Product(
    val cnlAcDtt: String?,
    val cnlOdrNo: String?,
    val dcPrice: Int?,
    val delYn: String?,
    val dlvrCmpltDtt: String?,
    val goodsBadgeImgPath: String?,
    val goodsBadgeSeq: Long?,
    val goodsCd: String?,
    val goodsCntQty: Int?,
    val goodsDispYn: String?,
    val goodsGroupCd: String?,
    val goodsGroupNm: String?,
    val goodsGroupOptnNm: String?,
    val goodsGroupOptnSeq: Int?,
    val goodsGroupOptnValue: String?,
    val goodsNm: String?,
    val goodsNrm: String?,
    val goodsOdrNo: String?,
    val goodsOptnUseYn: String?,
    val goodsStat: String?,
    val goodsSuplmtImgSeq: Int?,
    val imgPath: String?,
    val maxBuyPsblCntQty: Int?,
    val minBuyPsblCntQty: Int?,
    val mnrBuyYn: String?,
    val odrExchngRtgsOdrNo: String?,
    val slePrice: Int?,
    val splyPrice: Int?,
    val taxtnYn: String?,
    val tpCd: String?
)



data class Pagination(
    val currentPage: Int,
    val elementSizeOfPage: Int,
    val totalElements: Int,
    val totalPage: Int
)