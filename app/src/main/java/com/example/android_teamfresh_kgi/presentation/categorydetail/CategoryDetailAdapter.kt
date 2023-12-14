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
    private val context: Context,
    private val viewModel: CategoryDetailViewModel,
) :
    RecyclerView.Adapter<CategoryDetailAdapter.CategoryHolder>() {

    init {
        setHasStableIds(true)
    }

    private var subCategoryInfoList = mutableListOf<DomainAppSubDispClasInfo>()

    // Category 선택 변수
    private var selectPos = 0
    private var beforePos = selectPos
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
        beforePos = selectPos
        selectPos = position
    }
    fun getBeforePos() = beforePos

    fun updateItemList(items: MutableList<DomainAppSubDispClasInfo>) {


        subCategoryInfoList.add(
            DomainAppSubDispClasInfo(
                dispClasSeq = 0,
                subDispClasNm = "전체",
                dispClasCd = "",
                dispClasLvl = "",
                prntsDispClasSeq = 0
            )
        ) // 전체 서브 카테고리
        subCategoryInfoList.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return subCategoryInfoList[position].dispClasSeq.toLong()
    }


    inner class CategoryHolder(private val binding: ItemCategoryDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DomainAppSubDispClasInfo, position: Int) {
            binding.subCategory = item
            binding.viewModel = viewModel
            binding.position = position

            if (selectPos == position) {
                // 선택된 item 이라면
                binding.tvName.setTextColor(getColor(context, R.color.black))
            } else {
                binding.tvName.setTextColor(getColor(context, R.color.text_gray))
            }


            binding.executePendingBindings()
        }

    }

}