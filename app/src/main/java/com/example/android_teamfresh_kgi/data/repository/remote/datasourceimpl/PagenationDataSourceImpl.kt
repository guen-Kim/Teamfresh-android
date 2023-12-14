package com.example.android_teamfresh_kgi.data.repository.remote.datasourceimpl

import com.example.android_teamfresh_kgi.data.model.Pagination
import com.example.android_teamfresh_kgi.data.repository.remote.api.CategoryProductCheckApi
import com.example.android_teamfresh_kgi.data.repository.remote.datasource.PagenationDataSource
import com.example.android_teamfresh_kgi.data.utils.base.BaseDataSource
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class PagenationDataSourceImpl @Inject constructor(private val categoryProductCheckApi: CategoryProductCheckApi) :
    BaseDataSource(), PagenationDataSource {
    override suspend fun checkPagenation(
        remoteErrorEmitter: RemoteErrorEmitter,
        dispClasSeq: Int,
        subDispClasSeq: Int,
        order: String
    ): Pagination? {
        return safeApiCall(remoteErrorEmitter) {
            categoryProductCheckApi.getProduct(dispClasSeq, subDispClasSeq, page = 1, size = 1,order)
        }?.body()?.pagination
    }

}