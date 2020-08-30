package com.example.data

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Akis on 30/08/2020.
 */
interface CountryApi {

    @GET("countries")
    fun getCountries() : Call<CountriesResponse>
}