package com.example.android_teamfresh_kgi.app.di

import com.example.android_teamfresh_kgi.data.repository.remote.datasource.DetailCategoryDataSource
import com.example.android_teamfresh_kgi.data.repository.repositoryimpl.MainCategoryRepositoryImpl
import com.example.android_teamfresh_kgi.data.repository.remote.datasource.MajorCategoryDataSource
import com.example.android_teamfresh_kgi.data.repository.remote.datasource.QuickMenuDataSource
import com.example.android_teamfresh_kgi.data.repository.repositoryimpl.DetailCategoryRepositoryImpl
import com.example.android_teamfresh_kgi.domain.repository.DetailCategoryRepository
import com.example.android_teamfresh_kgi.domain.repository.MainCategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMainCategoryRepository(
        majorCategoryDataSource: MajorCategoryDataSource,
        quickMenuDataSource: QuickMenuDataSource
    ): MainCategoryRepository {
        return MainCategoryRepositoryImpl(majorCategoryDataSource, quickMenuDataSource)
    }


    @Provides
    @Singleton
    fun provideDetailCategoryRepository(
        detailCategoryDataSource: DetailCategoryDataSource
    ): DetailCategoryRepository {
        return DetailCategoryRepositoryImpl(detailCategoryDataSource)
    }


}