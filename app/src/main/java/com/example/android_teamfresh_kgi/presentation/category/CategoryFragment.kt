package com.example.android_teamfresh_kgi.presentation.category


import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import com.example.android_teamfresh_kgi.R
import com.example.android_teamfresh_kgi.databinding.FragmentCategoryBinding
import com.example.android_teamfresh_kgi.presentation.base.BaseFragment


class CategoryFragment : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category){

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