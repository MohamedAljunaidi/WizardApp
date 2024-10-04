package com.assignment.hometab.data.wizard

import com.assignment.hometab.data.wizard.model.WizardDetailsResponse
import com.assignment.hometab.data.wizard.model.WizardsResponse
import com.assignment.result.NetworkResult
import com.assignment.services.ApiManager
import javax.inject.Inject

class WizardsService @Inject constructor(private val apiManager: ApiManager) {

    companion object {
        private const val WIZARD_ID_PARAMS ="wizardId"
        private const val PATH_GET_WIZARDS =
            "Wizards"
        private const val PATH_GET_WIZARDS_DETAILS =
            "Wizards/{${WIZARD_ID_PARAMS}}"


    }


    suspend fun getWizards(
    ): NetworkResult<List<WizardsResponse>> {
        return apiManager.getRequest(
            PATH_GET_WIZARDS
        )
    }

    suspend fun getWizardDetails(
        wizardID:String
    ): NetworkResult<WizardDetailsResponse> {
        return apiManager.getRequest(
            PATH_GET_WIZARDS_DETAILS, pathParams = mapOf(WIZARD_ID_PARAMS to wizardID)
        )
    }

}