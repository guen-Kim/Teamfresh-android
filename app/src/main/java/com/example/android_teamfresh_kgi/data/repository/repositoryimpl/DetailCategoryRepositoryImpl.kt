package com.example.android_teamfresh_kgi.data.repository.repositoryimpl

import com.example.android_teamfresh_kgi.data.mapper.Mapper
import com.example.android_teamfresh_kgi.data.repository.remote.datasource.DetailCategoryDataSource
import com.example.android_teamfresh_kgi.domain.model.DomainDetailCategoryResponse
import com.example.android_teamfresh_kgi.domain.repository.DetailCategoryRepository
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter
import javax.inject.Inject

class DetailCategoryRepositoryImpl @Inject constructor(private val detailCategoryDataSource: DetailCategoryDataSource): DetailCategoryRepository {
    override suspend fun checkDetailCategory(
        remoteErrorEmitter: RemoteErrorEmitter,
        clasSeq: Int
    ): DomainDetailCategoryResponse? {
        return Mapper.subCategoryResponseMapper(detailCategoryDataSource.checkDetailCategory(remoteErrorEmitter, clasSeq))

    }
}