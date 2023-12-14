package com.example.android_teamfresh_kgi.data.repository.repositoryimpl

import com.example.android_teamfresh_kgi.data.mapper.Mapper
import com.example.android_teamfresh_kgi.data.repository.remote.datasource.MajorCategoryDataSource
import com.example.android_teamfresh_kgi.data.repository.remote.datasource.QuickMenuDataSource
import com.example.android_teamfresh_kgi.domain.model.DomainMajorCategoryResponse
import com.example.android_teamfresh_kgi.domain.model.DomainQuickMenuResponse
import com.example.android_teamfresh_kgi.domain.repository.MainCategoryRepository
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter
import javax.inject.Inject


/* Repository Pattern */
class MainCategoryRepositoryImpl @Inject constructor(
    private val majorCategoryDataSource: MajorCategoryDataSource,
    private val quickMenuDataSource: QuickMenuDataSource
) : MainCategoryRepository {

    override suspend fun checkMajorCategory(remoteErrorEmitter: RemoteErrorEmitter): DomainMajorCategoryResponse? {
        return Mapper.majorCategoryMapper(
            majorCategoryDataSource.checkMajorCategory(remoteErrorEmitter)
        )
    }

    override suspend fun checkQuickMenu(remoteErrorEmitter: RemoteErrorEmitter): DomainQuickMenuResponse? {
        return Mapper.quickMenuResponseMapper(
            quickMenuDataSource.checkQuickMenuDataSource(remoteErrorEmitter)
        )
    }
}