package com.assignment.hometab.domain.wizard.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.wizard.model.Wizard
import com.assignment.hometab.domain.wizard.model.WizardDetails
import com.assignment.hometab.domain.wizard.model.WizardWithFavorite
import kotlinx.coroutines.flow.Flow

interface IWizardRemoteRepository {

    fun getWizards(): Flow<ResultWrapper<List<WizardWithFavorite>?>>

    fun getWizardDetails(wizardId: String): Flow<ResultWrapper<WizardDetails?>>

}