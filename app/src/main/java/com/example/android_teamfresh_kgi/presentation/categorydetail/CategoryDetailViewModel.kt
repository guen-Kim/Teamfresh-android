package com.example.android_teamfresh_kgi.presentation.categorydetail

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.android_teamfresh_kgi.R
import com.example.android_teamfresh_kgi.domain.model.DomainAppSubDispClasInfo
import com.example.android_teamfresh_kgi.domain.model.DomainDispClasData
import com.example.android_teamfresh_kgi.domain.model.DomainPagination
import com.example.android_teamfresh_kgi.domain.model.DomainProduct
import com.example.android_teamfresh_kgi.domain.usecase.CheckCategoryProductUseCase
import com.example.android_teamfresh_kgi.domain.usecase.CheckDetailCategoryUseCase
import com.example.android_teamfresh_kgi.domain.utils.ErrorType
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter
import com.example.android_teamfresh_kgi.domain.utils.ScreenState
import com.example.android_teamfresh_kgi.presentation.event.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val detailCategoryUseCase: CheckDetailCategoryUseCase,
    private val categoryProductUseCase: CheckCategoryProductUseCase
) :
    ViewModel(), RemoteErrorEmitter {


    // load 된 subCategory
    private var _subCategoryItem = MutableLiveData<Event<DomainDispClasData>>()
    val subCategoryItem: LiveData<Event<DomainDispClasData>> get() = _subCategoryItem


    // load된 pagination
    private var _pagination = MutableLiveData<DomainPagination>()
    val pagination: LiveData<DomainPagination> get() = _pagination


    // Safe Call Api State
    private var _apiCallEvent = MutableLiveData<Event<ScreenState>>()
    val apiCallEvent: LiveData<Event<ScreenState>> get() = _apiCallEvent

    var apiErrorMesses = "none"
    var apiErrorType = ErrorType.UNKNOWN


    // 라디오 버튼 선택 state
    private var _selectedRadio = MutableLiveData<Event<String>>()
    val selectedRadio: LiveData<Event<String>> get() = _selectedRadio


    // product 선택 state
    private var _selectedProduct = MutableLiveData<Event<String>>()
    val selectedProduct: LiveData<Event<String>> get() = _selectedProduct

    // subCategory 선택 state
    private var _selectedSubCategory = MutableLiveData<Event<Int>>()
    val selectedSubCategory: LiveData<Event<Int>> get() = _selectedSubCategory


    // subCategory 선택 position
    private var _selectedSubCategoryPosition = MutableLiveData<Event<Integer>>()
    val selectedSubCategoryPosition: LiveData<Event<Integer>> get() = _selectedSubCategoryPosition


    fun onRadioBtnSelected(view: View, checkedId: Int) {

        when (checkedId) {
            R.id.rbtn_recommend_order -> {
                _selectedRadio.value = Event("추천순")
            }

            R.id.rbtn_sale_order -> {
                _selectedRadio.value = Event("판매량순")
            }

            R.id.rbtn_low_price_order -> {
                _selectedRadio.value = Event("낮은가격순")

            }

            R.id.rbtn_high_price_order -> {
                _selectedRadio.value = Event("높은가격순")

            }

        }
    }


    fun onCategoryDetailClick(subCategory: DomainAppSubDispClasInfo, position: Integer) {
        _selectedSubCategory.value = Event(subCategory.dispClasSeq)
        _selectedSubCategoryPosition.value = Event(position)
    }

    fun onProductClick(product: DomainProduct) {
        _selectedProduct.value = Event(product.goodsCd.toString())
    }


    // sub Category
    fun loadSubCategory(clasSeq: Int) {

        viewModelScope.launch {
            detailCategoryUseCase.checkDetailCategory(this@CategoryDetailViewModel, clasSeq)
                .let { response ->
                    if (response != null) {
                        _subCategoryItem.value = Event(response.data)

                        _apiCallEvent.value = Event(ScreenState.LOADING)
                    } else {
                        _apiCallEvent.value = Event(ScreenState.ERROR)

                    }

                }
        }
    }

    fun loadPagination(
        dispClasSeq: Int,
        subDispClasSeq: Int,
        order: String
    ) {

        viewModelScope.launch {
            categoryProductUseCase.checkPagination(
                this@CategoryDetailViewModel,
                dispClasSeq,
                subDispClasSeq,
                order
            )
                .let { response ->
                    if (response != null) {
                        _pagination.value = response

                        _apiCallEvent.value = Event(ScreenState.LOADING)
                    } else {
                        _apiCallEvent.value = Event(ScreenState.ERROR)

                    }
                }

        }


    }

    // Product paging 데이터
    suspend fun loadCategoryProduct(
        dispClasSeq: Int,
        subDispClasSeq: Int,
        order: String
    ): Flow<PagingData<DomainProduct>> {
        return categoryProductUseCase.checkDetailCategory(dispClasSeq, subDispClasSeq, order)
            .cachedIn(viewModelScope)
    }

    // Safe call API 오류 메세지 및 에러 타입

    override fun onError(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onError(errorType: ErrorType) {
        TODO("Not yet implemented")
    }


}