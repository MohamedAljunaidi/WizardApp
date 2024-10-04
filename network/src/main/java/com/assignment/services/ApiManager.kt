package com.assignment.services

import com.assignment.extensions.getModel
import com.assignment.extensions.map
import com.assignment.extensions.safeApiCall
import com.assignment.extensions.substitutePathParams
import com.assignment.result.NetworkResult
import javax.inject.Inject

class ApiManager @Inject constructor(
    val services: ApiRequests,
) {

    suspend inline fun <reified T> getRequest(
        url: String,
        pathParams: Map<String, String>? = mapOf(),
        headersMap: Map<String, String>? = mapOf(),
        queryParamsMap: Map<String, Any?>? = mapOf()
    ): NetworkResult<T> =
        safeApiCall {
            services.getRequest(
                url = substitutePathParams(url, pathParams),
                headersMap = headersMap,
                queryParamsMap = queryParamsMap
            )
        }.map { response ->
                response?.body()?.getModel()
            }

}