package com.example.data

import com.example.domain.Country
import com.google.gson.annotations.SerializedName

/**
 * Created by Akis on 30/08/2020.
 */
data class CountriesResponse(
    @SerializedName("NewConfirmed") val newConfirmed: Int? = null,
    @SerializedName("TotalConfirmed") val totalConfirmed: Int? = null,
    @SerializedName("Countries") val countries: List<CountryResponse>? = null

)

data class CountryResponse(
    @SerializedName("Country") val countryName: String? = null,
    @SerializedName("CountryCode") val countryCode: String? = null,
    @SerializedName("Slug") val slug: String? = null,
    @SerializedName("NewConfirmed") val newConfirmed: Int? = null,
    @SerializedName("TotalConfirmed") val totalConfirmed: Int? = null,
    @SerializedName("NewDeaths") val newDeaths: Boolean? = null,
    @SerializedName("TotalDeaths") val totalDeaths: String? = null,
    @SerializedName("TotalRecovered") val totalRecovered: String? = null,
    @SerializedName("Date") val date: String? = null


)

fun CountryResponse.toCountryModel(): Country = Country(countryName, newConfirmed, totalConfirmed, date)