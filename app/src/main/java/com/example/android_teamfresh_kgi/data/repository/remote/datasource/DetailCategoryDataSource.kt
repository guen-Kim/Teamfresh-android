package com.example.android_teamfresh_kgi.data.repository.remote.datasource

import com.example.android_teamfresh_kgi.data.model.DetailCategoryResponse
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter

interface DetailCategoryDataSource {
    suspend fun checkDetailCategory(
        remoteErrorEmitter: RemoteErrorEmitter,
        clasSeq: Int
    ): DetailCategoryResponse?

}