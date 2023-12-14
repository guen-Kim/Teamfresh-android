package com.example.android_teamfresh_kgi.presentation.category


import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_teamfresh_kgi.R
import com.example.android_teamfresh_kgi.databinding.FragmentCategoryBinding
import com.example.android_teamfresh_kgi.presentation.base.BaseFragment
import com.example.android_teamfresh_kgi.presentation.event.EventObserver
import com.example.android_teamfresh_kgi.presentation.model.CategoryBar
import com.example.android_teamfresh_kgi.presentation.model.CategoryMenu
import com.example.android_teamfresh_kgi.presentation.model.CategoryTitle
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint //의존성 주입 요청
class CategoryFragment() : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {

    private val viewModel by viewModels<CategoryViewModel>() // viewModel 주입

    private lateinit var notiBage: ConstraintLayout

    //onCreateView
    override fun init() {
        // init databinding
        binding.fragment = this
        // LiveData 객체 범위 = fragment 수명주기
        binding.lifecycleOwner = viewLifecycleOwner


        notiBage = LayoutInflater.from(requireContext())
            .inflate(R.layout.notification_badge, null) as ConstraintLayout

        // 툴바를 액션바로 대체, noActionBar에서 menu 콜백 메서드 호출 위함.
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    //onViewCreated
    override fun inited() {
        setToolbar()
        setRecyclerView()
        setMenuClickEvent()


    }


    private fun setRecyclerView() {

        // 레이아웃 매니저 초기화, span 20
        val layoutManager = GridLayoutManager(context, 20)

        with(binding.rvCategorySection) {
            viewModel.mainCategoryItem.observe(viewLifecycleOwner) {


                // item span size 결정
                layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int =
                        if (it[position] is CategoryTitle || it[position] is CategoryBar) {
                            20
                        } else if (it[position] is CategoryMenu) {
                            5
                        } else {
                            4
                        }
                }
                // Layout manager 설정
                binding.rvCategorySection.layoutManager = layoutManager
                // MainMenuitem 전달
                val adapter = CategoryAdapter(viewModel).apply {
                    updateItemList(it.toMutableList())
                }
                // adapter 설정
                binding.rvCategorySection.adapter = adapter
            }
        }
    }


    private fun setMenuClickEvent() {
        viewModel.openQuickMenuEvent.observe(viewLifecycleOwner) {
            shortShowToast(getString(R.string.toast_message_dev))
        }

        viewModel.openMajorMenuEvent.observe(viewLifecycleOwner, EventObserver{
            findNavController().navigate(
                R.id.action_categoryFragment_to_categoryDetailActivity, bundleOf(
                    // key, value
                    "KEY_CATEGORY_SEQENCE" to it.dispClasSeq,
                )
            )
        })
    }





    private fun setToolbar() {
        // 툴바 메뉴 생성 및 이벤트 리스너
        activity?.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_toolbar, menu)

                val menuBell: MenuItem = menu.findItem(R.id.toolbar_menu_bell)
                // 알림 메세지가 있다고 가정 ...
                menuBell.actionView = notiBage // 뱃지 달기
                notiBage.setOnClickListener {
                    shortShowToast(getString(R.string.toast_message_dev))
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
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}
