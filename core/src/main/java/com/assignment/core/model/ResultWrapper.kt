package com.assignment.core.model


/**
 * [ResultWrapper] class responsible to hold response data from data layer to presentation layer
 *
 */
sealed class ResultWrapper<out T : Any?> {
    data class Success<out T : Any?>(val data: T) : ResultWrapper<T>()

    data class Error(val error: ResultException) : ResultWrapper<Nothing>()
}
