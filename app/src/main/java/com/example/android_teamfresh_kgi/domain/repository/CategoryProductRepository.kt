package com.example.android_teamfresh_kgi.domain.repository

import androidx.paging.PagingData
import com.example.android_teamfresh_kgi.domain.model.DomainPagination
import com.example.android_teamfresh_kgi.domain.model.DomainProduct
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter
import kotlinx.coroutines.flow.Flow

interface CategoryProductRepository {
    fun checkCategoryProduct(
        dispClasSeq: Int,
        subDispClasSeq: Int,
        order: String,
    ): Flow<PagingData<DomainProduct>>

    suspend fun checkPagination(
        remoteErrorEmitter: RemoteErrorEmitter,
        dispClasSeq: Int,
        subDispClasSeq: Int,
        order: String
    ): DomainPagination?

}