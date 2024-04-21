package com.sample.brigthcovetvmazesample.feature.data.base

import android.util.Log
import com.sample.brigthcovetvmazesample.feature.domain.common.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRemoteRepository {

    suspend fun <T> safeApiCall(
        call: suspend () -> T
    ): ResultState<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResultState.Success(call.invoke())
            } catch (t: Throwable) {
                Log.e("ERROR REQUEST", t.stackTraceToString())
                ResultState.Error(throwable = t)

            }
        }

    }
}