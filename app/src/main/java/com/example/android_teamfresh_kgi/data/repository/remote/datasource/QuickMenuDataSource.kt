package com.example.android_teamfresh_kgi.data.repository.remote.datasource

import com.example.android_teamfresh_kgi.data.model.QuickMenuResponse
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter

interface QuickMenuDataSource {

    suspend fun checkQuickMenuDataSource(remoteErrorEmitter: RemoteErrorEmitter):QuickMenuResponse?
}