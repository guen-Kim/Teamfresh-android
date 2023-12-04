package com.example.android_teamfresh_kgi.domain.usecaseimpl

import com.example.android_teamfresh_kgi.domain.model.DomainMajorCategoryResponse
import com.example.android_teamfresh_kgi.domain.model.DomainQuickMenuResponse
import com.example.android_teamfresh_kgi.domain.repository.MainCategoryRepository
import com.example.android_teamfresh_kgi.domain.usecase.CheckMainCategoryUseCase
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class CheckMainCategoryUseCaseImpl @Inject constructor(private val mainCategoryRepository: MainCategoryRepository) :
    CheckMainCategoryUseCase {
    override suspend fun checkMajorCategory(remoteErrorEmitter: RemoteErrorEmitter): DomainMajorCategoryResponse? =
        mainCategoryRepository.checkMajorCategory(remoteErrorEmitter)

    override suspend fun checkQuickMenu(remoteErrorEmitter: RemoteErrorEmitter): DomainQuickMenuResponse? =
        mainCategoryRepository.checkQuickMenu(remoteErrorEmitter)


}