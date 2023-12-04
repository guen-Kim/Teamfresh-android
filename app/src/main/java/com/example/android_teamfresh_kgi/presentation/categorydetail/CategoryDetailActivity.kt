package com.example.android_teamfresh_kgi.presentation.categorydetail

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.android_teamfresh_kgi.R
import com.example.android_teamfresh_kgi.databinding.ActivityCategoryDetailBinding
import com.example.android_teamfresh_kgi.domain.model.DomainAppSubDispClasInfo
import com.example.android_teamfresh_kgi.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryDetailActivity :
    BaseActivity<ActivityCategoryDetailBinding>(R.layout.activity_category_detail) {

    private val viewModel by viewModels<CategoryDetailViewModel>() // viewModel 주입
    override fun init() {
        (this as AppCompatActivity).setSupportActionBar(binding.toolbar)
        //ActionBar 기본 Title 제거
        supportActionBar?.setDisplayShowTitleEnabled(false)



        setToolbar()

        val intent = intent
        val sqe = intent.getIntExtra("KEY_CATEGORY_SEQENCE", 0)

        shortShowToast(sqe.toString())

        viewModel.loadSubCategory(sqe)

        setRecyclerView()

    }

    private fun setRecyclerView() {
        binding.rvCategoryDetail

        val categoryAdapter = CategoryDetailAdapter(viewModel, object : CategoryDetailAdapter.OnItemClickListener {
            override fun setOnItemClickListener(item: DomainAppSubDispClasInfo){

            }
        })


        val decorationV = DividerItemDecoration(applicationContext, 0)
        val decorationH = DividerItemDecoration(applicationContext, 1)


        viewModel.subCategoryItem.observe(this) {

            binding.tvTitle.text = it.dispClasNm

            categoryAdapter.updateItemList(it.appSubDispClasInfoList.toMutableList())
        }

        binding.rvCategoryDetail.addItemDecoration(decorationV)
        binding.rvCategoryDetail.addItemDecoration(decorationH)


        binding.rvCategoryDetail.adapter = categoryAdapter

    }


    private fun setToolbar() {
        // 툴바 메뉴 생성 및 이벤트 리스너
        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_toolbar, menu)

                //          val menuBell: MenuItem = menu.findItem(R.id.toolbar_menu_bell)
//                // 알림 메세지가 있다고 가정 ...
//                menuBell.actionView = notiBage // 뱃지 달기
//                notiBage.setOnClickListener {
//                    shortShowToast("개발 중")
//                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // 메뉴 선택시 핸들링
                when (menuItem.itemId) {
                    R.id.toolbar_menu_bell -> {
                        shortShowToast("개발 중")
                        return true
                    }

                    R.id.toolbar_menu_setting -> {
                        shortShowToast("개발 중")
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