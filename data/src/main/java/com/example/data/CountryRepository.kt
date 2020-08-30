package com.example.data

import com.example.data.general.ApiFactory
import com.example.domain.Country
import com.example.domain.Resource

class CountryRepository private constructor(private val countryRemoteDataSource: CountryRemoteDataSource) {

    fun getCountries() : Resource<List<Country>?> = countryRemoteDataSource.getCountries()

    companion object {
        @Volatile private var instance: CountryRepository? = null

        fun getInstance() = instance ?: create()

        private fun create(): CountryRepository = synchronized(this) {
            instance ?: CountryRepository(CountryRemoteDataSource(ApiFactory.countryApi)).also { instance = it }
        }
    }
}