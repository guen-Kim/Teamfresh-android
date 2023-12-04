package com.example.android_teamfresh_kgi.domain.usecase

import com.example.android_teamfresh_kgi.domain.model.DomainMajorCategoryResponse
import com.example.android_teamfresh_kgi.domain.model.DomainQuickMenuResponse
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter

// 카테고리 페이지 API 조회하는 유스케이스
interface CheckMainCategoryUseCase {
    suspend fun checkMajorCategory(remoteErrorEmitter: RemoteErrorEmitter): DomainMajorCategoryResponse?
    suspend fun checkQuickMenu(remoteErrorEmitter: RemoteErrorEmitter): DomainQuickMenuResponse?
}