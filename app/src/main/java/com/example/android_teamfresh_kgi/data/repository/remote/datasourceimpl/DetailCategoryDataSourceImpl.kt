package com.example.android_teamfresh_kgi.data.repository.remote.datasourceimpl

import com.example.android_teamfresh_kgi.data.model.DetailCategoryResponse
import com.example.android_teamfresh_kgi.data.repository.remote.api.DetailCategoryCheckApi
import com.example.android_teamfresh_kgi.data.repository.remote.datasource.DetailCategoryDataSource
import com.example.android_teamfresh_kgi.data.utils.base.BaseDataSource
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class DetailCategoryDataSourceImpl @Inject constructor(private val detailCategoryCheckApi: DetailCategoryCheckApi) :
    BaseDataSource(), DetailCategoryDataSource {
    override suspend fun checkDetailCategory(
        remoteErrorEmitter: RemoteErrorEmitter,
        clasSeq: Int
    ): DetailCategoryResponse? {
        return safeApiCall(remoteErrorEmitter) {
            detailCategoryCheckApi.getDetailCategory(clasSeq)
        }?.body()
    }
}