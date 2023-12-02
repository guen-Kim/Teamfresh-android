package com.example.android_teamfresh_kgi.data.mapper

import com.example.android_teamfresh_kgi.data.model.MajorCartegoryResponse
import com.example.android_teamfresh_kgi.data.model.QuickMenuResponse
import com.example.android_teamfresh_kgi.domain.model.DomainMajorCategoryResponse
import com.example.android_teamfresh_kgi.domain.model.DomainQuickMenuResponse

/* data Model to domain Model */

object Mapper {

    fun MajorCategoryMapper(majorCartegoryResponse: MajorCartegoryResponse?): DomainMajorCategoryResponse? {
        return if (majorCartegoryResponse != null) {
            DomainMajorCategoryResponse(
                dispClasSeq = majorCartegoryResponse.dispClasSeq,
                dispClasNm = majorCartegoryResponse.dispClasNm,
                dispClasImgPath = majorCartegoryResponse.dispClasImgPath,
                dispClasCd = majorCartegoryResponse.dispClasCd,
            )
        } else {
            majorCartegoryResponse
        }
    }

    fun QuickMenuResponseMapper(quickMenuResponse: QuickMenuResponse?): DomainQuickMenuResponse? {
        return if (quickMenuResponse != null) {
            DomainQuickMenuResponse(
                quickMenuConcScrenIden = quickMenuResponse.quickMenuConcScrenIden,
                quickMenuConcScrenNm = quickMenuResponse.quickMenuConcScrenNm,
                quickMenuImgPath = quickMenuResponse.quickMenuImgPath,
                quickMenuMovScrenPath = quickMenuResponse.quickMenuMovScrenPath,
                quickMenuNm = quickMenuResponse.quickMenuNm,
                quickMenuSeq = quickMenuResponse.quickMenuSeq,
            )
        } else {
            quickMenuResponse
        }
    }
}
