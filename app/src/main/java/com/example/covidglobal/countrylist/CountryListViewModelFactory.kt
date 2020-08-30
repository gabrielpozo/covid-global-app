package com.example.covidglobal.countrylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Akis on 27/08/2020.
 */
class CountryListViewModelFactory(private val getCountries : GetCountries) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryListViewModel::class.java)) {
            return CountryListViewModel(getCountries) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}