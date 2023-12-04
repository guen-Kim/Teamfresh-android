package com.example.android_teamfresh_kgi.data.mapper

import com.example.android_teamfresh_kgi.data.model.MajorCategoryResponse
import com.example.android_teamfresh_kgi.data.model.QuickMenuResponse
import com.example.android_teamfresh_kgi.data.model.DetailCategoryResponse
import com.example.android_teamfresh_kgi.domain.model.DomainAppSubDispClasInfo
import com.example.android_teamfresh_kgi.domain.model.DomainDispClasData
import com.example.android_teamfresh_kgi.domain.model.DomainDispClasItem
import com.example.android_teamfresh_kgi.domain.model.DomainMajorCategoryResponse
import com.example.android_teamfresh_kgi.domain.model.DomainMajorDispClasItem
import com.example.android_teamfresh_kgi.domain.model.DomainQuickMenuResponse
import com.example.android_teamfresh_kgi.domain.model.DomainDetailCategoryResponse

/* data Model to domain Model */
object Mapper {

    fun majorCategoryMapper(majorCartegoryResponse: MajorCategoryResponse?): DomainMajorCategoryResponse? {
        return if (majorCartegoryResponse != null) {
            val data = mutableListOf<DomainMajorDispClasItem>()
            majorCartegoryResponse.data.forEach {
                data.add(
                    DomainMajorDispClasItem(
                        it.dispClasSeq,
                        it.dispClasNm,
                        it.dispClasImgPath,
                        it.dispClasCd,
                    )
                )
            }
            DomainMajorCategoryResponse(
                data = data,
                message = majorCartegoryResponse.message,
            )
        } else {
            majorCartegoryResponse
        }
    }

    fun quickMenuResponseMapper(quickMenuResponse: QuickMenuResponse?): DomainQuickMenuResponse? {
        return if (quickMenuResponse != null) {

            val data = mutableListOf<DomainDispClasItem>()
            quickMenuResponse.data.forEach {
                data.add(
                    DomainDispClasItem(
                        it.quickMenuSeq,
                        it.quickMenuNm,
                        it.quickMenuImgPath,
                        it.quickMenuConcScrenNm,
                        it.quickMenuConcScrenIden,
                        it.quickMenuMovScrenPath
                    )
                )
            }
            DomainQuickMenuResponse(
                data = data,
                message = quickMenuResponse.message,
            )
        } else {
            quickMenuResponse
        }
    }

    fun subCategoryResponseMapper(detailCategoryResponse: DetailCategoryResponse?): DomainDetailCategoryResponse? {
        return if (detailCategoryResponse != null) {

            val dispClassInfoList = mutableListOf<DomainAppSubDispClasInfo>()

            detailCategoryResponse.data.appSubDispClasInfoList.forEach {
                dispClassInfoList.add(
                    DomainAppSubDispClasInfo(
                        dispClasSeq = it.dispClasSeq,
                        subDispClasNm = it.subDispClasNm,
                        prntsDispClasSeq = it.prntsDispClasSeq,
                        dispClasCd = it.dispClasCd,
                        dispClasLvl = it.dispClasLvl,
                    )
                )
            }

            val dispClasData =
                DomainDispClasData(detailCategoryResponse.data.dispClasNm, dispClassInfoList)

            DomainDetailCategoryResponse(data = dispClasData, detailCategoryResponse.message)
        } else {
            detailCategoryResponse
        }


    }

}
