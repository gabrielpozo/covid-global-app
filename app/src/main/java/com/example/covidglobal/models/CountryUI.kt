package com.example.covidglobal.models

import android.os.Parcelable
import com.example.domain.Country
import kotlinx.android.parcel.Parcelize

/**
 * Created by Akis on 30/08/2020.
 */


// NOTE: This class can be ignored and only use domain object, but depends on
// the complexity of the domain object, having UI models can avoid undesired
// scenarios for having fields in presentation layer that are not necessary.

@Parcelize
data class CountryUI(
    val countryName: String?,
    val newConfirmed: Int?,
    val totalConfirmed: Int?,
    val date: String?,
    val countryCode: String?
) : Parcelable

fun Country.toCountryUIModel(): CountryUI {
    return CountryUI(
        countryName,
        newConfirmed,
        totalConfirmed,
        date,
        countryCode
    )
}