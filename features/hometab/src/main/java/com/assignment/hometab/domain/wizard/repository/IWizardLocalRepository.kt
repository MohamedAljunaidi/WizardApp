package com.assignment.hometab.domain.wizard.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.wizard.model.Favorite
import com.assignment.hometab.domain.wizard.model.Wizard
import com.assignment.hometab.domain.wizard.model.WizardDetails
import com.assignment.hometab.domain.wizard.model.WizardWithFavorite
import kotlinx.coroutines.flow.Flow

interface IWizardLocalRepository {

    fun getWizardWithFavorite(): Flow<ResultWrapper<List<WizardWithFavorite>?>>
    fun getFavorites(): Flow<ResultWrapper<List<WizardWithFavorite>?>>
    fun insertWizards(wizard: List<Wizard>?): Flow<ResultWrapper<Unit?>>
    fun insertFavorite(favorite: Favorite): Flow<ResultWrapper<Unit?>>

    fun getWizardDetails(wizardId: String): Flow<ResultWrapper<WizardDetails?>>
    fun insertWizardDetails(wizardDetails: WizardDetails?): Flow<ResultWrapper<Unit?>>
}