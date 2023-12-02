package com.example.android_teamfresh_kgi.data.repository.remote.datasourceimpl

import com.example.android_teamfresh_kgi.data.model.MajorCartegoryResponse
import com.example.android_teamfresh_kgi.data.repository.remote.api.MajorCategoryCheckApi
import com.example.android_teamfresh_kgi.data.repository.remote.datasource.MajorCategoryDataSource
import com.example.android_teamfresh_kgi.data.utils.base.BaseDataSource
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter

class MajorCategoryDataSourceImpl(private val majorCategoryCheckApi: MajorCategoryCheckApi) :
    BaseDataSource(),
    MajorCategoryDataSource {
    override suspend fun checkMajorCategory(remoteErrorEmitter: RemoteErrorEmitter): MajorCartegoryResponse? {
        return safeApiCall(remoteErrorEmitter) {
            majorCategoryCheckApi.getMajorCategory()
        }?.body()
    }
}