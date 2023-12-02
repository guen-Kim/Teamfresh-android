package com.example.android_teamfresh_kgi.data.repository.remote.api

import com.example.android_teamfresh_kgi.data.model.QuickMenuResponse
import retrofit2.Response
import retrofit2.http.GET

interface QuickMenuCheckApi {
    @GET("/app/main-infos/quick-menu")
    suspend fun getQuickMenu():Response<QuickMenuResponse>

}