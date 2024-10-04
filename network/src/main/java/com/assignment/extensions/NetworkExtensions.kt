package com.assignment.extensions

import com.assignment.core.model.ServerExceptionType
import com.assignment.exceptions.NetworkException
import com.assignment.model.ApiErrors
import com.assignment.network.common.NetworkConstants
import com.assignment.result.NetworkResult
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

inline fun <T> safeApiCall(
    apiCall: () -> Response<T>
): NetworkResult<Response<T>> {
    return runCatching {
        val response = apiCall()
        if (!response.isSuccessful) {
            throw HttpException(response)
        }
        response
    }.toNetworkResult()
}


inline fun <T, R> NetworkResult<T>.map(
    mapper: (T?) -> R?
): NetworkResult<R> {
    return when (this) {
        is NetworkResult.Success -> {
            runCatching {
                mapper(data)
            }.toNetworkResult()
        }

        is NetworkResult.Error -> {
            NetworkResult.Error(error)
        }
    }
}


fun <T> NetworkResult<T>.toCompletable(): NetworkResult<Unit> {
    return when (this) {
        is NetworkResult.Success -> {
            NetworkResult.Success(Unit)
        }

        is NetworkResult.Error -> {
            NetworkResult.Error(error)
        }
    }
}

fun <T> Result<T?>.toNetworkResult(): NetworkResult<T> = fold(
    onSuccess = { result ->
        NetworkResult.Success(result)
    },
    onFailure = { error ->
        val errorModel = error.parseErrorResponse()
        NetworkResult.Error(errorModel)
    }
)



inline fun NetworkResult<Unit>.onComplete(action: () -> Unit): NetworkResult<Unit> {
    if (this is NetworkResult.Success) action()
    return this
}

inline fun <T> NetworkResult<T>.onSuccess(action: (value: T?) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Success) action(data)
    return this
}

inline fun <T> NetworkResult<T>.onFailure(action: (exception: NetworkException) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Error) action(error)
    return this
}

fun Throwable.parseErrorResponse() = mapApiError(this)


private fun mapApiError(error: Throwable): NetworkException =
    when (error) {
        is IOException -> NetworkException.NoInternetFoundException()
        is HttpException -> {
            val serverExceptionType = when {

                error.isForbidden() -> ServerExceptionType.Forbidden

                error.isServerError() -> ServerExceptionType.ServerError

                else -> ServerExceptionType.ClientError
            }


            val apiErrors = error.response()?.errorBody()?.getModel<ApiErrors>()
            NetworkException.ApiErrorException(serverExceptionType, apiErrors)
        }

        else -> NetworkException.UnknownException(cause = error)
    }


private fun HttpException.isForbidden() =
    code() == NetworkConstants.ErrorCodes.CLIENT_ERROR_FORBIDDEN


private fun HttpException.isClientError() =
    code() in NetworkConstants.ErrorCodes.CLIENT_ERROR_RANGE_START..NetworkConstants.ErrorCodes.CLIENT_ERROR_RANGE_END

private fun HttpException.isServerError() =
    code() in NetworkConstants.ErrorCodes.SERVER_ERROR_RANGE_START..NetworkConstants.ErrorCodes.SERVER_ERROR_RANGE_END

