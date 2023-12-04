package com.example.android_teamfresh_kgi.domain.repository

import com.example.android_teamfresh_kgi.domain.model.DomainDetailCategoryResponse
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter

interface DetailCategoryRepository {
    suspend fun checkDetailCategory(
        remoteErrorEmitter: RemoteErrorEmitter,
        clasSeq: Int
    ): DomainDetailCategoryResponse?

}