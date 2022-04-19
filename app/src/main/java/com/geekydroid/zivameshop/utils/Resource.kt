package com.geekydroid.zivameshop.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>(message: String) : Resource<T>(message = message)
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Error<T>(error: String) : Resource<T>(message = error)
}
