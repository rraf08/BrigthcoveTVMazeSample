package com.sample.brigthcovetvmazesample.feature.domain.common

sealed class ResultState<T>(
    val data: T? = null,
    val throwable: Throwable? = null
) {
    class Success<T>(data: T) : ResultState<T>(data = data)
    class Loading<T>(data: T? = null) : ResultState<T>(data = data)
    class Error<T>(data: T? = null, throwable: Throwable? = null) :
        ResultState<T>(data = data, throwable = throwable)


}