package com.example.android_teamfresh_kgi.data.repository.remote.datasourceimpl

import com.example.android_teamfresh_kgi.data.model.QuickMenuResponse
import com.example.android_teamfresh_kgi.data.repository.remote.api.QuickMenuCheckApi
import com.example.android_teamfresh_kgi.data.repository.remote.datasource.QuickMenuDataSource
import com.example.android_teamfresh_kgi.data.utils.base.BaseDataSource
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter

class QuickMenuDataSourceImpl(private val quickMenuCheckApi:QuickMenuCheckApi) : BaseDataSource(), QuickMenuDataSource {
    override suspend fun checkQuickMenuDataSource(remoteErrorEmitter: RemoteErrorEmitter): QuickMenuResponse? {
        return safeApiCall(remoteErrorEmitter) {
            quickMenuCheckApi.getQuickMenu()
        }?.body()
    }
}