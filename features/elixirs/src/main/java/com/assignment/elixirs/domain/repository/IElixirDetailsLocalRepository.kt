package com.assignment.elixirs.domain.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.elixirs.domain.model.ElixirDetails
import kotlinx.coroutines.flow.Flow

interface IElixirDetailsLocalRepository {

    fun getElixirDetails(elixirId: String): Flow<ResultWrapper<ElixirDetails?>>
    fun insertElixirDetails(elixirDetails: ElixirDetails?): Flow<ResultWrapper<Unit?>>
}