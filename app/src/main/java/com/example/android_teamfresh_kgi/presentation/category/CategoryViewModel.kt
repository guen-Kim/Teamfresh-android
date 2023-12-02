package com.example.android_teamfresh_kgi.presentation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_teamfresh_kgi.domain.model.DomainMajorCategoryResponse
import com.example.android_teamfresh_kgi.domain.model.DomainQuickMenuResponse
import com.example.android_teamfresh_kgi.domain.usecaseimpl.CheckMainCategoryUseCase
import com.example.android_teamfresh_kgi.domain.utils.ErrorType
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter
import com.example.android_teamfresh_kgi.domain.utils.ScreenState
import com.example.android_teamfresh_kgi.presentation.event.Event
import kotlinx.coroutines.launch

class CategoryViewModel(private val mainCategoryUseCase: CheckMainCategoryUseCase) : ViewModel(),
    RemoteErrorEmitter {
    init {
        loadMainCategory()
    }


    private var _apiCallEvent = MutableLiveData<Event<ScreenState>>()
    val apiCallEvent: LiveData<Event<ScreenState>> get() = _apiCallEvent


    var apiCallMajorCategoryResult = DomainMajorCategoryResponse(0, "", "", "")
    var apiCallQuickMenuResult = DomainQuickMenuResponse("", "", "", "", "", 0)


    var apiErrorType = ErrorType.UNKNOWN
    var apiErrorMesses = "none"

    fun loadMainCategory() =
        viewModelScope.launch {
            mainCategoryUseCase.checkMajorCategory(this@CategoryViewModel)
                .let { response ->
                    if (response != null) {
                        apiCallMajorCategoryResult = response
                        _apiCallEvent.value = Event(ScreenState.LOADING)
                    } else
                        _apiCallEvent.value = Event(ScreenState.ERROR)

                }
            viewModelScope.launch {
                mainCategoryUseCase.checkQuickMenu(this@CategoryViewModel)
                    .let {response ->
                        if (response != null) {
                            apiCallQuickMenuResult = response
                            _apiCallEvent.value = Event(ScreenState.LOADING)
                        } else
                            _apiCallEvent.value = Event(ScreenState.ERROR)
                    }
            }
        }


    override fun onError(msg: String) {
        apiErrorMesses = msg

    }

    override fun onError(errorType: ErrorType) {
        apiErrorType = errorType
    }


}