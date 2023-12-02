package com.example.android_teamfresh_kgi.domain.usecaseimpl

import com.example.android_teamfresh_kgi.domain.model.DomainMajorCategoryResponse
import com.example.android_teamfresh_kgi.domain.model.DomainQuickMenuResponse
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter

interface CheckMainCategoryUseCase {
    suspend fun checkMajorCategory(remoteErrorEmitter: RemoteErrorEmitter): DomainMajorCategoryResponse?
    suspend fun checkQuickMenu(remoteErrorEmitter: RemoteErrorEmitter): DomainQuickMenuResponse?
}