package com.assignment.elixirs.data.repository

import com.assignment.caching.extensions.tryMapperQuery
import com.assignment.caching.manager.CachingManager
import com.assignment.caching.manager.ProviderEnum
import com.assignment.core.model.ResultWrapper
import com.assignment.elixirs.data.mapper.toElixirDetails
import com.assignment.elixirs.data.mapper.toElixirDetailsEntity
import com.assignment.elixirs.domain.model.ElixirDetails
import com.assignment.elixirs.domain.repository.IElixirDetailsLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ElixirLocalRepository(private val cachingManager: CachingManager) :
    IElixirDetailsLocalRepository {

    override fun getElixirDetails(elixirId: String): Flow<ResultWrapper<ElixirDetails?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getElixirDetails(elixirId)
        })
        { weather ->
            weather?.toElixirDetails()
        }
        emit(result)
    }


    override fun insertElixirDetails(elixirDetails: ElixirDetails?): Flow<ResultWrapper<Unit?>> =
        flow {
            val result = tryMapperQuery({
                elixirDetails?.toElixirDetailsEntity()?.let {
                    cachingManager.getProvider(ProviderEnum.ROOM)
                        .insertElixirDetails(it)
                }
            }) {}
            emit(result)
        }
}
