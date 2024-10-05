package com.assignment.hometab.data.wizard.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.extensions.tryRequest
import com.assignment.hometab.data.wizard.WizardsService
import com.assignment.hometab.data.wizard.mapper.toWizardDetails
import com.assignment.hometab.domain.wizard.repository.IWizardRemoteRepository
import com.assignment.hometab.data.wizard.mapper.toWizardList
import com.assignment.hometab.data.wizard.mapper.toWizardWithFavoriteList
import com.assignment.hometab.domain.wizard.model.WizardDetails
import com.assignment.hometab.domain.wizard.model.WizardWithFavorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WizardRemoteRepository @Inject constructor(
    private val wizardsService: WizardsService
) : IWizardRemoteRepository {

    override fun getWizards(): Flow<ResultWrapper<List<WizardWithFavorite>?>> = flow {
        val result = tryRequest(
            request = {
                wizardsService.getWizards(
                )
            },
            dataToDomain = { response ->
                response?.toWizardList()?.toWizardWithFavoriteList()
            }
        )
        emit(result)
    }


    override fun getWizardDetails(wizardId: String): Flow<ResultWrapper<WizardDetails?>> = flow {
        val result = tryRequest(
            request = {
                wizardsService.getWizardDetails(
                    wizardId
                )
            },
            dataToDomain = { response ->
                response?.toWizardDetails()
            }
        )
        emit(result)
    }


}