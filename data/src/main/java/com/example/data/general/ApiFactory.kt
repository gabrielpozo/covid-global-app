package com.example.data.general

import com.example.data.CountryApi

/**
 * Created by Akis on 30/08/2020.
 */
object ApiFactory {
    val countryApi: CountryApi = RetrofitHelper.getInstance().create(CountryApi::class.java)

}