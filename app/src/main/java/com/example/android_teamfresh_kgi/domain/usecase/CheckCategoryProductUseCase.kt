package com.example.android_teamfresh_kgi.domain.usecase


import androidx.paging.PagingData
import com.example.android_teamfresh_kgi.domain.model.DomainPagination
import com.example.android_teamfresh_kgi.domain.model.DomainProduct
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter
import kotlinx.coroutines.flow.Flow


// 패이징 처리한 DATA 가져오는 유스케이스
interface CheckCategoryProductUseCase {
    suspend fun checkDetailCategory(
        dispClasSeq: Int,
        subDispClasSeq: Int,
        order: String,
    ): Flow<PagingData<DomainProduct>>

    suspend fun checkPagination(
        remoteErrorEmitter: RemoteErrorEmitter,
        dispClasSeq: Int,
        subDispClasSeq: Int,
        order: String
    ):DomainPagination?

}