package com.example.android_teamfresh_kgi.data.repository.remote.api

import com.example.android_teamfresh_kgi.data.model.MajorCategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface MajorCategoryCheckApi {
    @GET("/app/disp-clas-infos/disp-clas-nm")
    suspend fun getMajorCategory() : Response<MajorCategoryResponse>
}