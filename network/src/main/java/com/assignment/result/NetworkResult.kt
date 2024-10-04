package com.assignment.result

import com.assignment.exceptions.NetworkException


sealed class NetworkResult<out T> {

    data class Success<T>(val data: T?) : NetworkResult<T>()

    data class Error(val error: NetworkException) : NetworkResult<Nothing>()

}