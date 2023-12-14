package com.example.android_teamfresh_kgi.domain.repository

import com.example.android_teamfresh_kgi.domain.model.DomainPagination
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter

interface PagenationRepository {
    fun checkPagenation(
        remoteErrorEmitter: RemoteErrorEmitter,
        dispClasSeq: Int,
        subDispClasSeq: Int,
        order: String
    ) : DomainPagination?

}