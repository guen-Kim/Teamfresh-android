package com.example.android_teamfresh_kgi.presentation.categorydetail

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.android_teamfresh_kgi.R
import com.example.android_teamfresh_kgi.databinding.ActivityCategoryDetailBinding
import com.example.android_teamfresh_kgi.presentation.base.BaseActivity
import com.example.android_teamfresh_kgi.presentation.event.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CategoryDetailActivity :
    BaseActivity<ActivityCategoryDetailBinding>(R.layout.activity_category_detail) {

    private val viewModel by viewModels<CategoryDetailViewModel>() // viewModel 주입

    private var selectedRadioOrder: String = "추천순" // 라디오 버튼
    private var subSeq: Int = 0 // 서브 카테고리 선택

    override fun init() {

        val productAdapter = ProductAdapter(viewModel)
        val categoryDetailAdapter = CategoryDetailAdapter(this, viewModel)

        binding.viewModel = viewModel

        // 이전 액티티비에서 데이터 가져오기
        val intent = intent
        val sqe = intent.getIntExtra("KEY_CATEGORY_SEQENCE", 0)

        // init
        loadInitData(sqe, productAdapter)
        setToolbar()
        setCategoryDetailRecyclerView(categoryDetailAdapter)
        setSelectObserver(sqe, categoryDetailAdapter)

    }


    fun setSelectObserver(sqe: Int, categoryDetailAdapter: CategoryDetailAdapter) {


        // 라디오 버튼 클릭
        viewModel.selectedRadio.observe(this, EventObserver {
            lifecycleScope.launch {
                selectedRadioOrder = it // 라디오 버튼 선택

                viewModel.loadCategoryProduct(sqe, subSeq!!, it).collectLatest { pagingData ->
                    val productAdapter = ProductAdapter(viewModel)
                    binding.rvProdectContainer.adapter = productAdapter
                    productAdapter.submitData(lifecycle, pagingData)
                }
            }

        })

        // sub category 클릭 API 호출
        viewModel.selectedSubCategory.observe(this, EventObserver {
            subSeq = it // sub category 선택
            Log.d("subCategory", subSeq.toString())

            lifecycleScope.launch {
                viewModel.loadCategoryProduct(sqe, it, selectedRadioOrder!!)
                    .collectLatest { pagingData ->
                        val productAdapter = ProductAdapter(viewModel)
                        binding.rvProdectContainer.adapter = productAdapter
                        productAdapter.submitData(lifecycle, pagingData)

                        // product pagination Api
                        viewModel.loadPagination(sqe, subSeq, selectedRadioOrder)
                    }


            }

        })

        //sub category 클릭 item position
        viewModel.selectedSubCategoryPosition.observe(this, EventObserver {
            categoryDetailAdapter.selectedItem(it.toInt())
            categoryDetailAdapter.notifyItemChanged(it.toInt())
            categoryDetailAdapter.notifyItemChanged(categoryDetailAdapter.getBeforePos())
        })


        //product 클릭
        viewModel.selectedProduct.observe(this, EventObserver {
            shortShowToast("개발 중 ")
        })
    }


    private fun loadInitData(sqe: Int, productAdapter: ProductAdapter) {

        // product Adapter 초기화
        binding.rvProdectContainer.adapter = productAdapter

        // product APi
        lifecycleScope.launch {
            viewModel.loadCategoryProduct(sqe, subSeq, selectedRadioOrder)
                .collectLatest { pagingData ->
                    productAdapter.submitData(lifecycle, pagingData)
                }
        }
        // product pagination Api
        viewModel.loadPagination(sqe, subSeq, selectedRadioOrder)


        // subCategory Api
        viewModel.loadSubCategory(sqe)


        // subCategory Count observe
        viewModel.pagination.observe(this@CategoryDetailActivity) {
            binding.tvProductCount.text = getString(R.string.product_count, it.totalElements)
        }

    }


    // UI 상단
    private fun setCategoryDetailRecyclerView(categoryDetailAdapter: CategoryDetailAdapter) {


        // toolbar를 actionbar로 설정
        (this as AppCompatActivity).setSupportActionBar(binding.toolbar)

        //ActionBar 기본 Title 제거
        supportActionBar?.setDisplayShowTitleEnabled(false)


        binding.rvCategoryDetail.adapter = categoryDetailAdapter


        // 구성변경 시 뷰홀더 item 단일 할당
        viewModel.subCategoryItem.observe(this, EventObserver {
            binding.tvTitle.text = it.dispClasNm
            categoryDetailAdapter.updateItemList(it.appSubDispClasInfoList.toMutableList())
        })
    }


    private fun setToolbar() {
        // 툴바 메뉴 생성 및 이벤트 리스너
        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.menu_toolbar, menu)


                val leffItem: MenuItem? = menu.findItem(R.id.toolbar_menu_bell)
                val rightItem: MenuItem? = menu.findItem(R.id.toolbar_menu_setting)

                leffItem?.setIcon(R.drawable.cart_menu_24)
                rightItem?.setIcon(R.drawable.search_menu_24)


                // 상단 뒤로가기 네비게이션
                binding.toolbar.setNavigationOnClickListener {
                    finish()
                }


            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // 메뉴 선택시 핸들링
                when (menuItem.itemId) {
                    R.id.toolbar_menu_bell -> {
                        shortShowToast(getString(R.string.toast_message_dev))
                        return true
                    }

                    R.id.toolbar_menu_setting -> {
                        shortShowToast(getString(R.string.toast_message_dev))
                        return true
                    }

                    else -> {
                        return false
                    }

                }
                return true
            }
        }, this, Lifecycle.State.RESUMED)
    }


}