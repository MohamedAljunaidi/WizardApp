package com.assignment.exceptions

import com.assignment.core.model.ErrorModel
import com.assignment.core.model.ResultException
import com.assignment.core.model.ServerExceptionType
import com.assignment.extensions.toErrorModel
import com.assignment.model.ApiErrors

sealed class NetworkException(
    errorModel: ErrorModel? = null
) : ResultException(errorModel) {

    class NoInternetFoundException :
        NetworkException(ErrorModel(ServerExceptionType.NoInternetConnection))

    class ApiErrorException(
        serverExceptionType: ServerExceptionType = ServerExceptionType.UnExpectedError,
        apiErrors: ApiErrors? = null
    ) : NetworkException(
        errorModel = apiErrors.toErrorModel(serverExceptionType)
    )


    class UnknownException(
        cause: Throwable
    ) : NetworkException(cause.toErrorModel(ServerExceptionType.UnExpectedError))
}