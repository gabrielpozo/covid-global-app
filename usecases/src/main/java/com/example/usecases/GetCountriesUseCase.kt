package com.example.usecases

import com.example.data.CountryRepository
import com.example.domain.Country
import com.example.domain.ResourceException
import com.example.domain.ResourceStatus
import com.example.usecases.general.BaseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetCountriesUseCase(
    private val countryRepository: CountryRepository

) : BaseUseCase<List<Country>, Void>() {
    override fun execute(
        scope: CoroutineScope,
        onSuccess: (List<Country>) -> Unit,
        onError: (ResourceException?) -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            val resource = withContext(Dispatchers.IO) { countryRepository.getCountries() }
            when (resource.state) {
                ResourceStatus.SUCCESS -> {
                    val list = resource.value?.sortedByDescending { it.newConfirmed }
                    when {
                        list.isNullOrEmpty() -> onError(ResourceException.NullOrEmptyResource("Null or empty list"))
                        else -> onSuccess(list)
                    }
                }
                ResourceStatus.ERROR -> {
                    onError(resource.error)
                }
            }
        }
    }


}