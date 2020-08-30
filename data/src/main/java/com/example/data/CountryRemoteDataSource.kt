package com.example.data

import com.example.domain.Country
import com.example.domain.Resource
import com.example.domain.ResourceException

/**
 * Created by Akis on 30/08/2020.
 */
class CountryRemoteDataSource(private val api: CountryApi) {

    fun getCountries(): Resource<List<Country>?> {
        lateinit var resource: Resource<List<Country>?>
        try {
            val response = api.getCountries().execute()
            when {
                response.isSuccessful -> {
                    val countries = response.body()
                    resource = Resource.success(countries?.countries?.map { it.toCountryModel() })
                }
                else -> {
                    // Parse error response if API is ready for it
                    resource = Resource.error(ResourceException.ApiError("CountryRemoteDataSource - API error"))
                }
            }
        } catch (exception: Exception) {
            resource = Resource.error(
                ResourceException.RemoteResponseError("CountryRemoteDataSource - Exception error", exception)
            )
        }

        return resource
    }
}