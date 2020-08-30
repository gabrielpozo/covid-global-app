package com.example.covidglobal.countrylist

import com.example.covidglobal.models.CountryUI

/**
 * Created by Akis on 28/08/2020.
 */
sealed class CountryListEvent {
    object ShowContent : CountryListEvent()

    object ShowEmptyListError : CountryListEvent()

    object ShowGeneralError : CountryListEvent()

    data class ShowCountryDetailsScreen(val country: CountryUI) : CountryListEvent()

}