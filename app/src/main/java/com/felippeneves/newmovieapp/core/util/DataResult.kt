package com.felippeneves.newmovieapp.core.util

sealed class DataResult<out T> {
    object Loading : DataResult<Nothing>()
    data class Success<out T>(val data: T?) : DataResult<T>()
    data class Failure(val e: Exception?) : DataResult<Nothing>()
}
