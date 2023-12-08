package com.example.android_teamfresh_kgi.data.repository.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android_teamfresh_kgi.data.model.Product
import com.example.android_teamfresh_kgi.data.repository.remote.api.CategoryProductCheckApi
import kotlinx.coroutines.delay
import javax.inject.Inject


private const val STARTING_KEY = 0 // API page 0 부터 시작

class ProductPagingDataSource @Inject constructor(
    private val categoryProductCheckApi: CategoryProductCheckApi,
    private val dispClasSeq: Int,
    private val subDispClasSeq: Int,
    private val order: String
) : PagingSource<Int, Product>() {


    // 사용자가 스크롤할 데이터를 비동기식으로 가져오기 위해 load() 함수를 호출
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        Log.d("pagingSize", "load")

        val page = params.key ?: STARTING_KEY // 최초 요청 페이지

        Log.d("Mykey", page.toString())
        val size = params.loadSize // 로드할 size

        Log.d("pagingSize", "${size}")

        return try {
            // api 요청
            val response = categoryProductCheckApi.getProduct(
                dispClasSeq = dispClasSeq,
                subDispClasSeq = subDispClasSeq,
                page = page, // totalElements / 20
                size = size, // 60 -> 20 -> ...
                order = order,
            ).body()



            if (page != STARTING_KEY) {
                delay(500)
            }

            // 페이징 및 넘버링
            if (response != null) {
                LoadResult.Page(
                    data = response.productList,
                    prevKey = if (page == 0) null else (page - 1),
                    nextKey = if (page == response.pagination.totalPage) null else (page + 1)
                )

            } else {


                LoadResult.Error(throw Exception("No Response"))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }


}