package com.example.covidglobal.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covidglobal.R
import com.example.covidglobal.customviews.ListAdapterWithHeader
import com.example.covidglobal.databinding.LayoutCountryItemBinding
import com.example.covidglobal.databinding.LayoutRecyclerViewHeaderBinding
import com.example.covidglobal.models.CountryUI

/**
 * Created by Akis on 30/08/2020.
 */

class CountriesAdapter(
    private val listener: Listener?
) : ListAdapterWithHeader<CountryUI, RecyclerView.ViewHolder>(CountriesAdapterItemCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER_TYPE
            else -> ITEM_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_TYPE -> HeaderViewHolder(
                LayoutRecyclerViewHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ItemViewHolder(
                LayoutCountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                listener
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_TYPE -> {
                val itemViewHolder = (holder as ItemViewHolder)
                val country = getItem(position)
                itemViewHolder.apply {
                    bind(country)
                    itemView.tag = country
                }
            }
        }
    }

    class ItemViewHolder(
        private val binding: LayoutCountryItemBinding,
        listener: Listener?
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val country = this@ItemViewHolder.binding.country
                country?.let { item ->
                    listener?.onClick(item)
                }
            }
        }

        fun bind(item: CountryUI) {
            binding.apply {
                country = item
                executePendingBindings()
            }
        }
    }

    class HeaderViewHolder(binding: LayoutRecyclerViewHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.title = binding.root.context.resources.getString(R.string.app_name)
        }
    }

    interface Listener {
        fun onClick(country: CountryUI)
    }

    companion object {
        private const val HEADER_TYPE = 0
        private const val ITEM_TYPE = 1
    }

}