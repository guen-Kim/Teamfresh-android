package com.example.android_teamfresh_kgi.presentation.categorydetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.android_teamfresh_kgi.R
import com.example.android_teamfresh_kgi.databinding.ItemCategoryDetailBinding
import com.example.android_teamfresh_kgi.domain.model.DomainAppSubDispClasInfo

class CategoryDetailAdapter(
    private val context : Context,
    private val viewModel: CategoryDetailViewModel,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<CategoryDetailAdapter.CategoryHolder>() {

    private var subCategoryInfoList = mutableListOf<DomainAppSubDispClasInfo>()
    private var selectPos = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryHolder(
            ItemCategoryDetailBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return subCategoryInfoList.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(subCategoryInfoList[position], position)
    }

    fun selectedItem(position: Int) {
        selectPos = position
    }

    fun updateItemList(items: MutableList<DomainAppSubDispClasInfo>) {
        subCategoryInfoList.addAll(items)
        notifyDataSetChanged()
    }

    inner class CategoryHolder(private val binding: ItemCategoryDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DomainAppSubDispClasInfo, position: Int) {
            binding.subCategory = item

            if (selectPos == position) {
                // 선택된 item 이라면
                binding.tvName.setTextColor(getColor(context, R.color.black))
            } else {
                binding.tvName.setTextColor(getColor(context, R.color.text_gray))
            }


            binding.tvName.setOnClickListener {
                listener.setOnItemClickListener(item)
                val beforePos = selectPos
                selectPos = position

                notifyItemChanged(beforePos)
                notifyItemChanged(selectPos)
            }

            binding.executePendingBindings()
        }

    }

    interface OnItemClickListener {
        fun setOnItemClickListener(item: DomainAppSubDispClasInfo)
    }

}