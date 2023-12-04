package com.example.android_teamfresh_kgi.app.di

import com.example.android_teamfresh_kgi.domain.repository.DetailCategoryRepository
import com.example.android_teamfresh_kgi.domain.repository.MainCategoryRepository
import com.example.android_teamfresh_kgi.domain.usecase.CheckDetailCategoryUseCase
import com.example.android_teamfresh_kgi.domain.usecaseimpl.CheckMainCategoryUseCaseImpl
import com.example.android_teamfresh_kgi.domain.usecase.CheckMainCategoryUseCase
import com.example.android_teamfresh_kgi.domain.usecaseimpl.CheckDetailCategoryUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideMainCategoryUseCase(mainCategoryRepository: MainCategoryRepository): CheckMainCategoryUseCase {
        return CheckMainCategoryUseCaseImpl(mainCategoryRepository)
    }


    @Provides
    @Singleton
    fun provideDetailCategoryUseCase(detailCategoryRepository: DetailCategoryRepository): CheckDetailCategoryUseCase {
        return CheckDetailCategoryUseCaseImpl(detailCategoryRepository)
    }
}