package com.example.usecases.general

import com.example.domain.ResourceException
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Akis on 30/08/2020.
 */
abstract class BaseUseCase<ResultType, ParameterType> {
    abstract fun execute(
        scope: CoroutineScope,
        onSuccess: (ResultType) -> Unit,
        onError: (ResourceException?) -> Unit
    )
}