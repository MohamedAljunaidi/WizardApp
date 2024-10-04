package com.assignment.hometab.domain.wizard.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.wizard.model.Wizard
import com.assignment.hometab.domain.wizard.model.WizardDetails
import kotlinx.coroutines.flow.Flow

interface IWizardRemoteRepository {

    fun getWizards(): Flow<ResultWrapper<List<Wizard>?>>

    fun getWizardDetails(wizardId: String): Flow<ResultWrapper<WizardDetails?>>

}