package com.assignment.hometab.domain.home.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.home.model.Wizard
import kotlinx.coroutines.flow.Flow

interface IWizardRemoteRepository {

    fun getWizards(): Flow<ResultWrapper<List<Wizard>?>>

}