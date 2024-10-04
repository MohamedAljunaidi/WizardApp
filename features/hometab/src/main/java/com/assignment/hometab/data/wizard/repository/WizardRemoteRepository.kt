package com.assignment.hometab.data.wizard.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.extensions.tryRequest
import com.assignment.hometab.data.WizardsService
import com.assignment.hometab.domain.home.model.Wizard
import com.assignment.hometab.domain.home.repository.IWizardRemoteRepository
import com.assignment.hometab.data.mapper.toWizards
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WizardRemoteRepository @Inject constructor(
    private val wizardsService: WizardsService
) : IWizardRemoteRepository {

    override fun getWizards(): Flow<ResultWrapper<List<Wizard>?>> = flow {
        val result = tryRequest(
            request = {
                wizardsService.getWizards(
                )
            },
            dataToDomain = { response ->
                response?.toWizards()
            }
        )
        emit(result)
    }

}