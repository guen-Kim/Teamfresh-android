package com.example.android_teamfresh_kgi.presentation.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_teamfresh_kgi.R
import com.example.android_teamfresh_kgi.databinding.ItemCategoryBarSectionBinding
import com.example.android_teamfresh_kgi.databinding.ItemCategoryMenuSectionBinding
import com.example.android_teamfresh_kgi.databinding.ItemCategoryTitleSectionBinding


private const val VIEWTYPE_MENU = 0
private const val VIEWTYPE_BAR = 1
private const val VIEWTYPE_TITLE = 2

class CategoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val categoryItems = mutableListOf<CategoryItemData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEWTYPE_MENU -> MenuViewHolder(
                ItemCategoryMenuSectionBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )

            VIEWTYPE_BAR -> BarViewHolder(
                ItemCategoryBarSectionBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )

            else -> TitleViewHolder(
                ItemCategoryTitleSectionBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return categoryItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (categoryItems[position]) {
            is CategoryMenu -> VIEWTYPE_MENU
            is CategoryBar -> VIEWTYPE_BAR
            else -> VIEWTYPE_TITLE
        }
    }

    fun submitItemList(items: List<CategoryItemData>) {

        // menu와 quick menu 사이에 bar 추가하기



        // update DataSet


    }




    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MenuViewHolder -> {
                val item = categoryItems[position] as CategoryMenu
                holder.bind(item)
            }

            is BarViewHolder -> {
                val item = categoryItems[position] as CategoryBar
                holder.bind(item)
            }

            is TitleViewHolder -> {
                val item = categoryItems[position] as CategoryTitle
                holder.bind(item)
            }

        }
    }


//* Category ViewHolder Section

    inner class MenuViewHolder(private val binding: ItemCategoryMenuSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryMenu) {
            binding.ivCategoryImage.setImageResource(R.drawable.badge_new)
            binding.tvCategoryName.text = item.name
            binding.executePendingBindings()
        }
    }


    inner class BarViewHolder(private val binding: ItemCategoryBarSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryBar) {}
    }

    inner class TitleViewHolder(private val binding: ItemCategoryTitleSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryTitle) {
            binding.tvCategoryTitle.text = item.title
        }
    }
}
