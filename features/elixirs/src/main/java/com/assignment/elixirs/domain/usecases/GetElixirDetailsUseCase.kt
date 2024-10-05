package com.assignment.elixirs.domain.usecases

import com.assignment.core.bases.BaseUseCase
import com.assignment.core.extensions.networkBoundResource
import com.assignment.core.extensions.resultWrapperData
import com.assignment.core.model.ResultWrapper
import com.assignment.elixirs.domain.model.ElixirDetails
import com.assignment.elixirs.domain.repository.IElixirDetailsLocalRepository
import com.assignment.elixirs.domain.repository.IElixirDetailsRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

class GetElixirDetailsUseCase @Inject constructor(
    private val remoteRepository: IElixirDetailsRemoteRepository,
    private val localRepository: IElixirDetailsLocalRepository
) :
    BaseUseCase<String, Flow<ResultWrapper<ElixirDetails?>>> {
    private var response: Flow<ResultWrapper<ElixirDetails?>> = emptyFlow()

    override suspend fun invoke(params: String?): Flow<ResultWrapper<ElixirDetails?>> =
        networkBoundResource(
            queryDb = {
                localRepository.getElixirDetails(params ?: "")
            },
            fetchApi = {
                remoteRepository.getElixirDetails(params ?: "")
            },
            saveApiResult = { fetchResult ->
                fetchResult.collect { resultWrapper ->
                    this.response = flowOf(resultWrapper)

                    resultWrapperData(resultWrapper, { elixirDetails ->
                        localRepository.insertElixirDetails(
                            elixirDetails = elixirDetails
                        ).collect()
                    }, {
                        localRepository.getElixirDetails(params ?: "")
                    })
                }
            }, onQueryDbError = {
                response
            }
        )
}