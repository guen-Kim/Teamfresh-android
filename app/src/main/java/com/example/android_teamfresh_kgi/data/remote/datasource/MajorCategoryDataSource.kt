package com.example.android_teamfresh_kgi.data.remote.datasource

import com.example.android_teamfresh_kgi.data.model.MajorCartegoryResponse
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter

interface MajorCategoryDataSource {
    suspend fun checkMajorCategory(remoteErrorEmitter: RemoteErrorEmitter): MajorCartegoryResponse?

}