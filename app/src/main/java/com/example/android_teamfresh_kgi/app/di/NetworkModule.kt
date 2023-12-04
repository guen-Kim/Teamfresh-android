package com.example.android_teamfresh_kgi.app.di

import com.example.android_teamfresh_kgi.app.utils.Utils.BASE_URL
import com.example.android_teamfresh_kgi.data.repository.remote.api.DetailCategoryCheckApi
import com.example.android_teamfresh_kgi.data.repository.remote.api.MajorCategoryCheckApi
import com.example.android_teamfresh_kgi.data.repository.remote.api.QuickMenuCheckApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    //okHttp 의존성 주입 (아래 retrofit 의존성 주입에 사용)
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor())
            .build()
    }

    @Provides
    @Singleton
    //gson 의존성 주입 (아래 retrofit 의존성 주입에 사용)
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    //retrofit 의존성 주입 (아래 LoveCalculatorApi 의존성 주입에 사용)
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    //MajorCategoryCheckApi interface 의존성 주입
    fun provideMajorCategoryCheckApiService(retrofit: Retrofit): MajorCategoryCheckApi {
        return retrofit.create(MajorCategoryCheckApi::class.java)
    }

    @Provides
    @Singleton
    //QuickMenuCheckApi interface 의존성 주입
    fun provideQuickMenuCheckApiService(retrofit: Retrofit): QuickMenuCheckApi {
        return retrofit.create(QuickMenuCheckApi::class.java)
    }

    @Provides
    @Singleton
    //DetailCategoryCheckApi interface 의존성 주입
    fun provideDetailCategoryCheckApiService(retrofit: Retrofit): DetailCategoryCheckApi {
        return retrofit.create(DetailCategoryCheckApi::class.java)
    }



    // 저수준 네트워킹 로깅 확인
    private fun getLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

}