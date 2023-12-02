package com.example.android_teamfresh_kgi.data.repository.remote.datasource

import com.example.android_teamfresh_kgi.data.model.MajorCategoryResponse
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter

interface MajorCategoryDataSource {
    suspend fun checkMajorCategory(remoteErrorEmitter: RemoteErrorEmitter): MajorCategoryResponse?

}