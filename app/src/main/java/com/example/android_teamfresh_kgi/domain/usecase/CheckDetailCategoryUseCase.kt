package com.example.android_teamfresh_kgi.domain.usecase

import com.example.android_teamfresh_kgi.domain.model.DomainDetailCategoryResponse
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter

interface CheckDetailCategoryUseCase {
    suspend fun checkDetailCategory(remoteErrorEmitter: RemoteErrorEmitter, clasSeq: Int): DomainDetailCategoryResponse?
}