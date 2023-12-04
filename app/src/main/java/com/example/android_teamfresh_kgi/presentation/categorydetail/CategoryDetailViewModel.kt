package com.example.android_teamfresh_kgi.presentation.categorydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_teamfresh_kgi.domain.model.DomainDispClasData
import com.example.android_teamfresh_kgi.domain.usecase.CheckDetailCategoryUseCase
import com.example.android_teamfresh_kgi.domain.utils.ErrorType
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter
import com.example.android_teamfresh_kgi.domain.utils.ScreenState
import com.example.android_teamfresh_kgi.presentation.event.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryDetailViewModel @Inject constructor(private val detailCategoryUseCase: CheckDetailCategoryUseCase) :
    ViewModel(), RemoteErrorEmitter {


    private var _apiCallEvent = MutableLiveData<Event<ScreenState>>()
    val apiCallEvent: LiveData<Event<ScreenState>> get() = _apiCallEvent

    var apiErrorMesses = "none"
    var apiErrorType = ErrorType.UNKNOWN

    private var _subCategoryItem = MutableLiveData<DomainDispClasData>()
    val subCategoryItem: LiveData<DomainDispClasData> get() = _subCategoryItem



    // 서브 카테고리
    fun loadSubCategory(clasSeq: Int) {

        viewModelScope.launch {
            detailCategoryUseCase.checkDetailCategory(this@CategoryDetailViewModel, clasSeq)
                .let { response ->
                    if(response != null) {
                        _subCategoryItem.value = response.data

                        _apiCallEvent.value = Event(ScreenState.LOADING)
                    } else {
                        _apiCallEvent.value = Event(ScreenState.ERROR)

                    }

                }
        }

    }

    override fun onError(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onError(errorType: ErrorType) {
        TODO("Not yet implemented")
    }


}