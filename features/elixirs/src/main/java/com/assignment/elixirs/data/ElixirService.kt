package com.assignment.elixirs.data

import com.assignment.elixirs.data.model.ElixirDetailsResponse
import com.assignment.result.NetworkResult
import com.assignment.services.ApiManager
import javax.inject.Inject

class ElixirService @Inject constructor(private val apiManager: ApiManager) {

    companion object {
        private const val ELIXIR_ID_PARAMS ="elixirId"

        private const val PATH_GET_ELIXIR_DETAILS =
            "Elixirs/{${ELIXIR_ID_PARAMS}}"
    }

    suspend fun getElixirDetails(
        elixirId:String
    ): NetworkResult<ElixirDetailsResponse> {
        return apiManager.getRequest(
            PATH_GET_ELIXIR_DETAILS, pathParams = mapOf(ELIXIR_ID_PARAMS to elixirId)
        )
    }

}