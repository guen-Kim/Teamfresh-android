package com.example.android_teamfresh_kgi.domain.usecaseimpl


import androidx.paging.PagingData
import com.example.android_teamfresh_kgi.domain.model.DomainPagination
import com.example.android_teamfresh_kgi.domain.model.DomainProduct
import com.example.android_teamfresh_kgi.domain.repository.CategoryProductRepository
import com.example.android_teamfresh_kgi.domain.usecase.CheckCategoryProductUseCase
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckCategoryProductUseCaseImpl @Inject constructor(private val categoryProductRepository: CategoryProductRepository) :
    CheckCategoryProductUseCase {
    override suspend fun checkDetailCategory(
        dispClasSeq: Int,
        subDispClasSeq: Int,
        order: String,
    ): Flow<PagingData<DomainProduct>> = categoryProductRepository.checkCategoryProduct(
        dispClasSeq,
        subDispClasSeq,
        order)

    override suspend fun checkPagination(
        remoteErrorEmitter: RemoteErrorEmitter,
        dispClasSeq: Int,
        subDispClasSeq: Int,
        order: String
    ): DomainPagination? {
        return categoryProductRepository.checkPagination(remoteErrorEmitter, dispClasSeq, subDispClasSeq, order)
    }
}