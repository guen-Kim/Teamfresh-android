package com.example.android_teamfresh_kgi.domain.repository

import com.example.android_teamfresh_kgi.domain.model.DomainMajorCategoryResponse
import com.example.android_teamfresh_kgi.domain.model.DomainQuickMenuResponse
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter


// 페이지1 대분류, 퀵메뉴 dataSource 비지니스 로직
interface MainCategoryRepository {
    suspend fun checkMajorCategory(remoteErrorEmitter: RemoteErrorEmitter): DomainMajorCategoryResponse?
    suspend fun checkQuickMenu(remoteErrorEmitter: RemoteErrorEmitter): DomainQuickMenuResponse?

}