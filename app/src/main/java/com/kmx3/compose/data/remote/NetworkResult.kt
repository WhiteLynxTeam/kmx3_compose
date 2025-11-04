package com.kmx3.compose.data.remote

// Network слой — сетевые ошибки
sealed class NetworkResult<out T> {
    data class Success<T>(val data: T, val code: Int = 200) : NetworkResult<T>()
    data class HttpError(val code: Int, val message: String) : NetworkResult<Nothing>()
    data class NetworkError(val exception: Throwable) : NetworkResult<Nothing>()
}