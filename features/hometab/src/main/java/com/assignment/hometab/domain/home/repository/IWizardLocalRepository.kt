package com.assignment.hometab.domain.home.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.home.model.Wizard
import kotlinx.coroutines.flow.Flow

interface IWizardLocalRepository {

    fun getWizards(): Flow<ResultWrapper<List<Wizard>?>>
    fun insertWizards(wizard: List<Wizard>?): Flow<ResultWrapper<Unit?>>
}