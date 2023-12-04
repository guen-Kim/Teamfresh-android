package com.example.android_teamfresh_kgi.domain.usecaseimpl

import com.example.android_teamfresh_kgi.domain.model.DomainDetailCategoryResponse
import com.example.android_teamfresh_kgi.domain.repository.DetailCategoryRepository
import com.example.android_teamfresh_kgi.domain.usecase.CheckDetailCategoryUseCase
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class CheckDetailCategoryUseCaseImpl @Inject constructor(private val detailCategoryRepository: DetailCategoryRepository) : CheckDetailCategoryUseCase {
    override suspend fun checkDetailCategory(
        remoteErrorEmitter: RemoteErrorEmitter,
        clasSeq: Int
    ): DomainDetailCategoryResponse? =
        detailCategoryRepository.checkDetailCategory(remoteErrorEmitter, clasSeq)

}