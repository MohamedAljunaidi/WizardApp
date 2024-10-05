package com.assignment.elixirs.data.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.elixirs.data.ElixirService
import com.assignment.elixirs.data.mapper.toElixirDetails
import com.assignment.elixirs.domain.model.ElixirDetails
import com.assignment.elixirs.domain.repository.IElixirDetailsRemoteRepository
import com.assignment.extensions.tryRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ElixirRemoteRepository @Inject constructor(
    private val elixirService: ElixirService
) : IElixirDetailsRemoteRepository {


    override fun getElixirDetails(elixirId: String): Flow<ResultWrapper<ElixirDetails?>> = flow {
        val result = tryRequest(
            request = {
                elixirService.getElixirDetails(
                    elixirId
                )
            },
            dataToDomain = { response ->
                response?.toElixirDetails()
            }
        )
        emit(result)
    }


}