package com.assignment.hometab.domain.wizard.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.wizard.model.Wizard
import com.assignment.hometab.domain.wizard.model.WizardDetails
import kotlinx.coroutines.flow.Flow

interface IWizardLocalRepository {

    fun getWizards(): Flow<ResultWrapper<List<Wizard>?>>
    fun insertWizards(wizard: List<Wizard>?): Flow<ResultWrapper<Unit?>>

    fun getWizardDetails(wizardId: String): Flow<ResultWrapper<WizardDetails?>>
    fun insertWizardDetails(wizardDetails: WizardDetails?): Flow<ResultWrapper<Unit?>>
}