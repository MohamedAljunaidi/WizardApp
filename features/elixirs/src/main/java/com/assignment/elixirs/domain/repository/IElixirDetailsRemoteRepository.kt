package com.assignment.elixirs.domain.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.elixirs.domain.model.ElixirDetails
import kotlinx.coroutines.flow.Flow

interface IElixirDetailsRemoteRepository {

    fun getElixirDetails(elixirId: String): Flow<ResultWrapper<ElixirDetails?>>

}