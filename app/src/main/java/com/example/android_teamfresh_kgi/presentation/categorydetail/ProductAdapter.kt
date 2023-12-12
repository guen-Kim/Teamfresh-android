package com.example.android_teamfresh_kgi.presentation.categorydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android_teamfresh_kgi.databinding.ItemProductDetailBinding
import com.example.android_teamfresh_kgi.domain.model.DomainProduct

class ProductAdapter(private val viewModel: CategoryDetailViewModel) :
    PagingDataAdapter<DomainProduct, ProductAdapter.ProductViewHolder>(DIFF_CALLBACK) {


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DomainProduct>() {
            override fun areItemsTheSame(oldItem: DomainProduct, newItem: DomainProduct): Boolean {
                return oldItem.goodsCd == newItem.goodsCd
            }

            override fun areContentsTheSame(
                oldItem: DomainProduct,
                newItem: DomainProduct
            ): Boolean {
                return oldItem.goodsCd == newItem.goodsCd
            }

        }

    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(
            ItemProductDetailBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }


    inner class ProductViewHolder(private val binding: ItemProductDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DomainProduct) {
            binding.product = item
            binding.viewModel = viewModel

            binding.executePendingBindings()
        }
    }

}