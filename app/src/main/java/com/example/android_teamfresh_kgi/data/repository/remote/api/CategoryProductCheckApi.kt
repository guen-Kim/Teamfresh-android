package com.example.android_teamfresh_kgi.data.repository.remote.api

import com.example.android_teamfresh_kgi.data.model.ProductPagingInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryProductCheckApi {
    @GET("/app/disp-clas-infos/disp-clas/{dispClasSeq}/sub-disp-clas/{subDispClasSeq}/goods-infos?")
    suspend fun getProduct(
        @Path("dispClasSeq") dispClasSeq: Int,
        @Path("subDispClasSeq") subDispClasSeq: Int,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("searchValue") order: String,
    ):Response<ProductPagingInfoResponse>
}
//Query-> page={page}&size={size}&searchValue={order}