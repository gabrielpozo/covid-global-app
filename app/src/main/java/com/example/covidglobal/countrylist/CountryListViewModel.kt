package com.example.covidglobal.countrylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidglobal.general.SingleLiveEvent
import com.example.covidglobal.models.CountryUI
import com.example.covidglobal.models.toCountryUIModel
import com.example.domain.Country
import com.example.domain.ResourceException
import com.example.usecases.GetCountries

/**
 * Created by Akis on 27/08/2020.
 */
class CountryListViewModel(private val getCountries : GetCountries) : ViewModel() {

    private val events: SingleLiveEvent<CountryListEvent> = SingleLiveEvent()
    private val countriesLiveData = MutableLiveData<List<CountryUI>>()

    fun getEvents(): LiveData<CountryListEvent> = events

    fun getCountries(): LiveData<List<CountryUI>> = countriesLiveData

    fun start() {
        loadCountries()
    }

    private fun loadCountries() {
        getCountries.execute(
            onSuccess = { list: List<Country> ->
                countriesLiveData.value = list.map { it.toCountryUIModel() }
                events.value = CountryListEvent.ShowContent
            },
            onError = { resourceException: ResourceException? ->
                when (resourceException) {
                    is ResourceException.NullOrEmptyResource -> events.value = CountryListEvent.ShowEmptyListError
                    else -> events.value = CountryListEvent.ShowGeneralError
                }
            })
    }

    fun onCountryItemClicked(country: CountryUI) {
        events.value = CountryListEvent.ShowCountryDetailsScreen(country)
    }

    fun onRetryButtonPressed() {
        loadCountries()
    }
}