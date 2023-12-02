package com.example.android_teamfresh_kgi.presentation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_teamfresh_kgi.domain.usecaseimpl.CheckMainCategoryUseCase
import com.example.android_teamfresh_kgi.domain.utils.ErrorType
import com.example.android_teamfresh_kgi.domain.utils.RemoteErrorEmitter
import com.example.android_teamfresh_kgi.domain.utils.ScreenState
import com.example.android_teamfresh_kgi.presentation.event.Event
import com.example.android_teamfresh_kgi.presentation.model.CategoryBar
import com.example.android_teamfresh_kgi.presentation.model.CategoryItemData
import com.example.android_teamfresh_kgi.presentation.model.CategoryMenu
import com.example.android_teamfresh_kgi.presentation.model.CategoryTitle
import com.example.android_teamfresh_kgi.presentation.model.QuickMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(private val mainCategoryUseCase: CheckMainCategoryUseCase) :
    ViewModel(),
    RemoteErrorEmitter {
    init {
        loadMainCategory()
    }

    private var _apiCallEvent = MutableLiveData<Event<ScreenState>>()
    val apiCallEvent: LiveData<Event<ScreenState>> get() = _apiCallEvent

    var apiErrorMesses = "none"
    var apiErrorType = ErrorType.UNKNOWN


    private var _mainCategoryItem = MutableLiveData<List<CategoryItemData>>()
    val mainCategoryItem: LiveData<List<CategoryItemData>> get() = _mainCategoryItem

    // 카테고리 페이지
    fun loadMainCategory() =
        // 코루틴 순차적인 비동기 처리
        viewModelScope.launch {

            // 캐싱
            var mainCategoryItemList = mutableListOf<CategoryItemData>()

            // majorCategory api 요청
            mainCategoryUseCase.checkMajorCategory(this@CategoryViewModel)
                .let { response ->
                    if (response != null) {
                        // 응답된 데이터 1
                        response.data.forEach {
                            mainCategoryItemList.add(
                                CategoryMenu(
                                    it.dispClasSeq,
                                    it.dispClasNm,
                                    it.dispClasImgPath,
                                    it.dispClasCd
                                )
                            )
                        }
                        _apiCallEvent.value = Event(ScreenState.LOADING)
                    } else
                        _apiCallEvent.value = Event(ScreenState.ERROR)

                }
            if (mainCategoryItemList.size != 0) {
                // 정상적으로 응답되었다면
                mainCategoryItemList.add(CategoryBar) // view divide
                mainCategoryItemList.add(CategoryTitle("기획전/이벤트")) // view subTitle

            } else {
                // 정상적으로 응답되지 않았다면
                return@launch // 코루틴 중단
            }

            //QuickMenu api 요청
            mainCategoryUseCase.checkQuickMenu(this@CategoryViewModel)
                .let { response ->
                    if (response != null) {
                        // 응답된 데이터 2
                        response.data.forEach {
                            mainCategoryItemList.add(
                                QuickMenu(
                                    it.quickMenuSeq,
                                    it.quickMenuNm,
                                    it.quickMenuImgPath,
                                    it.quickMenuConcScrenNm,
                                    it.quickMenuConcScrenIden,
                                    it.quickMenuMovScrenPath,
                                )
                            )
                        }
                        // 최종 데이터 초기화
                        _mainCategoryItem.value = mainCategoryItemList
                        _apiCallEvent.value = Event(ScreenState.LOADING)
                    } else
                        _apiCallEvent.value = Event(ScreenState.ERROR)
                }

        }


    override fun onError(msg: String) {
        apiErrorMesses = msg

    }

    override fun onError(errorType: ErrorType) {
        apiErrorType = errorType
    }


}