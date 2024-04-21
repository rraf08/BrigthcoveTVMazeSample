package com.sample.brigthcovetvmazesample.feature.data.base

sealed class Failure(var msg: String?, var retryAction: () -> Unit) : Throwable() {

    class Timeout(msg: String? = null) : Failure(msg, {})

    class NoInternet(msg: String? = null) : Failure(msg, {})

    class Unknown(msg: String? = null) : Failure(msg, {})

}