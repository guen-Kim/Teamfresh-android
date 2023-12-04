package com.example.android_teamfresh_kgi.data.repository.remote.api

import com.example.android_teamfresh_kgi.data.model.DetailCategoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailCategoryCheckApi {

    @GET("/app/disp-clas-infos/disp-clas/{dispClasSeq}/sub-disp-clas-infos")
    suspend fun getDetailCategory(
        @Path("dispClasSeq") clasSeq:Int
    ) : Response<DetailCategoryResponse>
}