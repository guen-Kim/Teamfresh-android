package com.example.android_teamfresh_kgi.presentation.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_teamfresh_kgi.databinding.ItemCategoryBarSectionBinding
import com.example.android_teamfresh_kgi.databinding.ItemCategoryMenuSectionBinding
import com.example.android_teamfresh_kgi.databinding.ItemCategoryQuickSectionBinding
import com.example.android_teamfresh_kgi.databinding.ItemCategoryTitleSectionBinding
import com.example.android_teamfresh_kgi.presentation.model.CategoryBar
import com.example.android_teamfresh_kgi.presentation.model.CategoryItemData
import com.example.android_teamfresh_kgi.presentation.model.CategoryMenu
import com.example.android_teamfresh_kgi.presentation.model.CategoryTitle
import com.example.android_teamfresh_kgi.presentation.model.QuickMenu

private const val VIEWTYPE_MENU = 0
private const val VIEWTYPE_QUICK = 1
private const val VIEWTYPE_BAR = 2
private const val VIEWTYPE_TITLE = 3

class CategoryAdapter(private val viewModel: CategoryViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categoryItems = mutableListOf<CategoryItemData>()

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

            VIEWTYPE_QUICK -> QuickViewHolder(
                ItemCategoryQuickSectionBinding.inflate(
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

    override fun getItemId(position: Int): Long {
        return categoryItems[position].hashCode().toLong()
    }
    override fun getItemCount(): Int {
        return categoryItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (categoryItems[position]) {
            is CategoryMenu -> VIEWTYPE_MENU
            is QuickMenu -> VIEWTYPE_QUICK
            is CategoryBar -> VIEWTYPE_BAR
            else -> VIEWTYPE_TITLE
        }
    }

    fun updateItemList(items: MutableList<CategoryItemData>) {
        categoryItems.addAll(items)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MenuViewHolder -> {
                val item = categoryItems[position] as CategoryMenu
                holder.bind(item)
            }

            is QuickViewHolder -> {
                val item = categoryItems[position] as QuickMenu
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


/*  ViewHolder Section  */

    inner class MenuViewHolder(private val binding: ItemCategoryMenuSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryMenu) {
            binding.viewmodel = viewModel
            binding.menu = item
            binding.executePendingBindings()
        }
    }

    inner class QuickViewHolder(private val binding: ItemCategoryQuickSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: QuickMenu) {
            binding.viewmodel = viewModel
            binding.quick = item
            binding.executePendingBindings()
        }
    }


    inner class BarViewHolder(private val binding: ItemCategoryBarSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryBar) {
            binding.executePendingBindings()
        }
    }

    inner class TitleViewHolder(private val binding: ItemCategoryTitleSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryTitle) {
            binding.tvCategoryTitle.text = item.title
            binding.executePendingBindings()
        }
    }


}
