package com.assignment.hometab.domain.wizard.usecases

import com.assignment.core.bases.BaseUseCase
import com.assignment.core.extensions.networkBoundResource
import com.assignment.core.extensions.resultWrapperData
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.wizard.model.Wizard
import com.assignment.hometab.domain.wizard.model.WizardWithFavorite
import com.assignment.hometab.domain.wizard.repository.IWizardLocalRepository
import com.assignment.hometab.domain.wizard.repository.IWizardRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

class GetWizardListUseCase @Inject constructor(
    private val remoteRepository: IWizardRemoteRepository,
    private val localRepository: IWizardLocalRepository
) :
    BaseUseCase<Map<String, String>, Flow<ResultWrapper<List<WizardWithFavorite?>?>>> {
    private var response: Flow<ResultWrapper<List<WizardWithFavorite>?>> = emptyFlow()

    override suspend fun invoke(params: Map<String, String>?): Flow<ResultWrapper<List<WizardWithFavorite>?>> =
        networkBoundResource(
            queryDb = {
                localRepository.getWizardWithFavorite()
            },
            fetchApi = {
                remoteRepository.getWizards()
            },
            saveApiResult = { fetchResult ->
                fetchResult.collect { resultWrapper ->
                    this.response = flowOf(resultWrapper)

                    resultWrapperData(resultWrapper, { wizard ->
                        localRepository.insertWizards(wizard = wizard?.map { it.wizard }).collect()
                    }, {
                        localRepository.getWizardWithFavorite()
                    })
                }
            }, onQueryDbError = {
                response

            }
        )
}