package com.assignment.hometab.data

import com.assignment.hometab.data.wizard.model.WizardsResponse
import com.assignment.result.NetworkResult
import com.assignment.services.ApiManager
import javax.inject.Inject

class WizardsService @Inject constructor(private val apiManager: ApiManager) {

    companion object {
        private const val PATH_GET_WIZARDS =
            "Wizards"
    }


    suspend fun getWizards(
    ): NetworkResult<List<WizardsResponse>> {
        return apiManager.getRequest(
            PATH_GET_WIZARDS
        )
    }
}