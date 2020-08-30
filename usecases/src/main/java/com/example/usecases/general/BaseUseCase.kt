package com.example.usecases.general

import com.example.domain.ResourceException

/**
 * Created by Akis on 30/08/2020.
 */
abstract class BaseUseCase<ResultType, ParameterType> {
    abstract fun execute(
        params: ParameterType? = null,
        onSuccess: (ResultType) -> Unit,
        onError: (ResourceException?) -> Unit
    )
}