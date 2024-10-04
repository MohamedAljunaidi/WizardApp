package com.assignment.hometab.domain.wizard.usecases

import com.assignment.core.bases.BaseUseCase
import com.assignment.core.extensions.networkBoundResource
import com.assignment.core.extensions.resultWrapperData
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.wizard.model.WizardDetails
import com.assignment.hometab.domain.wizard.repository.IWizardLocalRepository
import com.assignment.hometab.domain.wizard.repository.IWizardRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

class GetWizardDetailsUseCase @Inject constructor(
    private val remoteRepository: IWizardRemoteRepository,
    private val localRepository: IWizardLocalRepository
) :
    BaseUseCase<String, Flow<ResultWrapper<WizardDetails?>>> {
    private var response: Flow<ResultWrapper<WizardDetails?>> = emptyFlow()

    override suspend fun invoke(params: String?): Flow<ResultWrapper<WizardDetails?>> =
        networkBoundResource(
            queryDb = {
                localRepository.getWizardDetails(params ?: "")
            },
            fetchApi = {
                remoteRepository.getWizardDetails(params ?: "")
            },
            saveApiResult = { fetchResult ->
                fetchResult.collect { resultWrapper ->
                    this.response = flowOf(resultWrapper)

                    resultWrapperData(resultWrapper, { wizardDetails ->
                        localRepository.insertWizardDetails(
                            wizardDetails = wizardDetails
                        ).collect()
                    }, {
                        localRepository.getWizardDetails(params ?: "")
                    })
                }
            }, onQueryDbError = {
                response
            }
        )
}