package com.example.android_teamfresh_kgi.presentation.category


import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_teamfresh_kgi.R
import com.example.android_teamfresh_kgi.databinding.FragmentCategoryBinding
import com.example.android_teamfresh_kgi.presentation.base.BaseFragment


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {
    private lateinit var notiBage: ConstraintLayout

    //onCreateView
    override fun init() {
        notiBage = LayoutInflater.from(requireContext())
            .inflate(R.layout.notification_badge, null) as ConstraintLayout
        // 툴바를 액션바로 대체, noActionBar에서 menu 콜백 메서드 호출 위함.
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    //onViewCreated
    override fun inited() {
        setToolbar()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        // 리스트 생성
        val arrayList = arrayListOf<CategoryItemData>()

        arrayList.add(CategoryMenu("사과", "", MenuType.NOMAL))
        arrayList.add(CategoryMenu("포도", "", MenuType.NOMAL))
        arrayList.add(CategoryMenu("바나나", "", MenuType.NOMAL))
        arrayList.add(CategoryMenu("자두", "", MenuType.NOMAL))
        arrayList.add(CategoryMenu("건포도", "", MenuType.NOMAL))


        arrayList.add(CategoryTitle("기획전/이벤트"))
        arrayList.add(CategoryMenu("포도2", "", MenuType.QUICK))
        arrayList.add(CategoryMenu("바나나2", "", MenuType.QUICK))
        arrayList.add(CategoryMenu("자두2", "", MenuType.QUICK))
        arrayList.add(CategoryMenu("건포도2", "", MenuType.QUICK))
        arrayList.add(CategoryMenu("배고파2", "", MenuType.QUICK))


        // 레이아웃 매니저 초기화
        val layoutManager = GridLayoutManager(context, 20)

        // Set span size
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int =
                if (arrayList[position] !is CategoryMenu) {
                    // section bar or section title 이라면, 사이즈 20
                    20
                } else {
                    val menuItem = arrayList[position] as CategoryMenu
                    if (menuItem.type == MenuType.NOMAL) {
                        // section menu 라면, 사이즈 5
                        5
                    } else {
                        // section quick menu라면, 사이즈 4
                        4
                    }
                }
        }

        //Set Layout manager
        binding.rvCategorySection.layoutManager = layoutManager

        // Set adapter
        val adapter = CategoryAdapter().apply {
            submitItemList(arrayList)
        }
        binding.rvCategorySection.adapter = adapter
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
                    shortShowToast("개발 중")
                }
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
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}
