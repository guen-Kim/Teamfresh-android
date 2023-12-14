package com.example.android_teamfresh_kgi.data.mapper

import com.example.android_teamfresh_kgi.data.model.MajorCategoryResponse
import com.example.android_teamfresh_kgi.data.model.QuickMenuResponse
import com.example.android_teamfresh_kgi.data.model.DetailCategoryResponse
import com.example.android_teamfresh_kgi.data.model.Pagination
import com.example.android_teamfresh_kgi.data.model.ProductPagingInfoResponse
import com.example.android_teamfresh_kgi.data.repository.remote.paging.ProductPagingDataSource
import com.example.android_teamfresh_kgi.domain.model.DomainAppSubDispClasInfo
import com.example.android_teamfresh_kgi.domain.model.DomainDispClasData
import com.example.android_teamfresh_kgi.domain.model.DomainDispClasItem
import com.example.android_teamfresh_kgi.domain.model.DomainMajorCategoryResponse
import com.example.android_teamfresh_kgi.domain.model.DomainMajorDispClasItem
import com.example.android_teamfresh_kgi.domain.model.DomainQuickMenuResponse
import com.example.android_teamfresh_kgi.domain.model.DomainDetailCategoryResponse
import com.example.android_teamfresh_kgi.domain.model.DomainPagination
import com.example.android_teamfresh_kgi.domain.model.DomainProduct
import com.example.android_teamfresh_kgi.domain.model.DomainProductPagingInfoResponse

/* data Model to domain Model */
object Mapper {

    fun majorCategoryMapper(majorCartegoryResponse: MajorCategoryResponse?): DomainMajorCategoryResponse? {
        return majorCartegoryResponse?.let {
            DomainMajorCategoryResponse(
                data = it.data.map { majorDispClasItem ->
                    DomainMajorDispClasItem(
                        majorDispClasItem.dispClasSeq,
                        majorDispClasItem.dispClasNm,
                        majorDispClasItem.dispClasImgPath,
                        majorDispClasItem.dispClasCd
                    )
                },
                message = it.message
            )
        }
    }

    fun quickMenuResponseMapper(quickMenuResponse: QuickMenuResponse?): DomainQuickMenuResponse? {
        return quickMenuResponse?.let {
            DomainQuickMenuResponse(
                data = it.data.map { quickMenu ->
                    DomainDispClasItem(
                        quickMenu.quickMenuSeq,
                        quickMenu.quickMenuNm,
                        quickMenu.quickMenuImgPath,
                        quickMenu.quickMenuConcScrenNm,
                        quickMenu.quickMenuConcScrenIden,
                        quickMenu.quickMenuMovScrenPath
                    )
                },
                message = it.message
            )
        }
    }

    fun subCategoryResponseMapper(detailCategoryResponse: DetailCategoryResponse?): DomainDetailCategoryResponse? {
        return detailCategoryResponse?.let {
            DomainDetailCategoryResponse(
                data = DomainDispClasData(
                    it.data.dispClasNm,
                    it.data.appSubDispClasInfoList.map { subDispClasInfo ->
                        DomainAppSubDispClasInfo(
                            dispClasSeq = subDispClasInfo.dispClasSeq,
                            subDispClasNm = subDispClasInfo.subDispClasNm,
                            prntsDispClasSeq = subDispClasInfo.prntsDispClasSeq,
                            dispClasCd = subDispClasInfo.dispClasCd,
                            dispClasLvl = subDispClasInfo.dispClasLvl
                        )
                    }
                ),
                message = it.message
            )
        }
    }


    fun paginationMapper(pagination: Pagination?): DomainPagination? {
        return DomainPagination( currentPage = pagination!!.currentPage,
            elementSizeOfPage = pagination.elementSizeOfPage,
            totalElements = pagination.totalElements,
            totalPage = pagination.totalPage
        )
    }

}
