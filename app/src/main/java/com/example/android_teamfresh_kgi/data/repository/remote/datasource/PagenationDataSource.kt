package com.example.android_teamfresh_kgi.data.repository.remote.datasource

import com.example.android_teamfresh_kgi.data.model.Pagination
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter

interface PagenationDataSource {
    suspend fun checkPagenation(
        remoteErrorEmitter: RemoteErrorEmitter,
        dispClasSeq: Int,
        subDispClasSeq: Int,
        order: String
    ) : Pagination?
}