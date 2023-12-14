package com.example.android_teamfresh_kgi.data.repository.repositoryimpl

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.android_teamfresh_kgi.data.mapper.Mapper
import com.example.android_teamfresh_kgi.data.model.Pagination
import com.example.android_teamfresh_kgi.data.repository.remote.api.CategoryProductCheckApi
import com.example.android_teamfresh_kgi.data.repository.remote.datasource.PagenationDataSource
import com.example.android_teamfresh_kgi.data.repository.remote.paging.ProductPagingDataSource
import com.example.android_teamfresh_kgi.domain.model.DomainPagination
import com.example.android_teamfresh_kgi.domain.model.DomainProduct
import com.example.android_teamfresh_kgi.domain.repository.CategoryProductRepository
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter
import hilt_aggregated_deps._com_example_android_teamfresh_kgi_presentation_categorydetail_CategoryDetailActivity_GeneratedInjector
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryProductRepositoryImpl @Inject constructor(
    private val categoryProductCheckApi: CategoryProductCheckApi,
    private val paginationDataSource: PagenationDataSource,
) : CategoryProductRepository {
    override fun checkCategoryProduct(
        dispClasSeq: Int,
        subDispClasSeq: Int,
        order: String,
    ): Flow<PagingData<DomainProduct>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ProductPagingDataSource(
                    categoryProductCheckApi,
                    dispClasSeq,
                    subDispClasSeq,
                    order
                )
            }).flow.map { pagingData ->
            pagingData.map { product ->
                // Product를 DomainProduct으로 변환
                DomainProduct(
                    cnlAcDtt = product.cnlAcDtt,
                    cnlOdrNo = product.cnlOdrNo,
                    dcPrice = product.dcPrice,
                    delYn = product.delYn,
                    dlvrCmpltDtt = product.dlvrCmpltDtt,
                    goodsBadgeImgPath = product.goodsBadgeImgPath,
                    goodsBadgeSeq = product.goodsBadgeSeq,
                    goodsCd = product.goodsCd,
                    goodsCntQty = product.goodsCntQty,
                    goodsDispYn = product.goodsDispYn,
                    goodsGroupCd = product.goodsGroupCd,
                    goodsGroupNm = product.goodsGroupNm,
                    goodsGroupOptnNm = product.goodsGroupOptnNm,
                    goodsGroupOptnSeq = product.goodsGroupOptnSeq,
                    goodsGroupOptnValue = product.goodsGroupOptnValue,
                    goodsNm = product.goodsNm,
                    goodsNrm = product.goodsNrm,
                    goodsOdrNo = product.goodsOdrNo,
                    goodsOptnUseYn = product.goodsOptnUseYn,
                    goodsStat = product.goodsStat,
                    goodsSuplmtImgSeq = product.goodsSuplmtImgSeq,
                    imgPath = product.imgPath,
                    maxBuyPsblCntQty = product.maxBuyPsblCntQty,
                    minBuyPsblCntQty = product.minBuyPsblCntQty,
                    mnrBuyYn = product.mnrBuyYn,
                    odrExchngRtgsOdrNo = product.odrExchngRtgsOdrNo,
                    slePrice = product.slePrice,
                    splyPrice = product.splyPrice,
                    taxtnYn = product.taxtnYn,
                    tpCd = product.tpCd
                )
            }
        }

    }

    override suspend fun checkPagination(
        remoteErrorEmitter: RemoteErrorEmitter,
        dispClasSeq: Int,
        subDispClasSeq: Int,
        order: String
    ): DomainPagination? {
       return Mapper.paginationMapper(paginationDataSource.checkPagenation(remoteErrorEmitter,dispClasSeq,subDispClasSeq,order))
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20 // 한 번에 PagingSource에 로드될 item 사이즈
    }

}