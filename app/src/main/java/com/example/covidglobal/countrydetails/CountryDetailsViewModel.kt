package com.example.covidglobal.countrydetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidglobal.models.CountryUI

/**
 * Created by Akis on 01/09/2020.
 */
class CountryDetailsViewModel : ViewModel() {

    private val countryLiveData = MutableLiveData<CountryUI>()

    fun getCountry(): LiveData<CountryUI> = countryLiveData

    fun start(country: CountryUI?) {
        countryLiveData.value = country
    }
}