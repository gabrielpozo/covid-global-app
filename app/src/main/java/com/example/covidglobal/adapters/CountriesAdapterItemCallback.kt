package com.example.covidglobal.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.covidglobal.models.CountryUI

/**
 * Created by Akis on 30/08/2020.
 */

class CountriesAdapterItemCallback : DiffUtil.ItemCallback<CountryUI>() {
    override fun areItemsTheSame(oldItem: CountryUI, newItem: CountryUI): Boolean {
        return oldItem.countryCode == newItem.countryCode
    }

    override fun areContentsTheSame(oldItem: CountryUI, newItem: CountryUI): Boolean {
        return oldItem == newItem
    }
}