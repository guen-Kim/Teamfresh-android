package com.example.android_teamfresh_kgi.app.di

import com.example.android_teamfresh_kgi.data.repository.remote.api.DetailCategoryCheckApi
import com.example.android_teamfresh_kgi.data.repository.remote.api.MajorCategoryCheckApi
import com.example.android_teamfresh_kgi.data.repository.remote.api.QuickMenuCheckApi
import com.example.android_teamfresh_kgi.data.repository.remote.datasource.DetailCategoryDataSource
import com.example.android_teamfresh_kgi.data.repository.remote.datasource.MajorCategoryDataSource
import com.example.android_teamfresh_kgi.data.repository.remote.datasource.QuickMenuDataSource
import com.example.android_teamfresh_kgi.data.repository.remote.datasourceimpl.DetailCategoryDataSourceImpl
import com.example.android_teamfresh_kgi.data.repository.remote.datasourceimpl.MajorCategoryDataSourceImpl
import com.example.android_teamfresh_kgi.data.repository.remote.datasourceimpl.QuickMenuDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {
    @Provides
    @Singleton
    fun bindQuickMenuDataSource(quickMenuCheckApi: QuickMenuCheckApi): QuickMenuDataSource {
        return QuickMenuDataSourceImpl(quickMenuCheckApi)
    }

    @Provides
    @Singleton
    fun bindMajorCategoryDataSource(majorCategoryCheckApi: MajorCategoryCheckApi): MajorCategoryDataSource {
        return MajorCategoryDataSourceImpl(majorCategoryCheckApi)
    }

    @Provides
    @Singleton
    fun bindDetailCategoryDataSource(detailCategoryCheckApi: DetailCategoryCheckApi): DetailCategoryDataSource {
        return DetailCategoryDataSourceImpl(detailCategoryCheckApi)
    }


}