package com.assignment.hometab.domain.home.usecases

import com.assignment.core.bases.BaseUseCase
import com.assignment.core.extensions.networkBoundResource
import com.assignment.core.extensions.resultWrapperData
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.home.model.Wizard
import com.assignment.hometab.domain.home.repository.IWizardLocalRepository
import com.assignment.hometab.domain.home.repository.IWizardRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

class GetWizardListUseCase @Inject constructor(
    private val remoteRepository: IWizardRemoteRepository,
    private val localRepository: IWizardLocalRepository
) :
    BaseUseCase<Map<String, String>, Flow<ResultWrapper<List<Wizard?>?>>> {
    private var response: Flow<ResultWrapper<List<Wizard>?>> = emptyFlow()

    override suspend fun invoke(params: Map<String, String>?): Flow<ResultWrapper<List<Wizard>?>> =
        networkBoundResource(
            queryDb = {
                localRepository.getWizards()
            },
            fetchApi = {
                remoteRepository.getWizards()
            },
            saveApiResult = { fetchResult ->
                fetchResult.collect { resultWrapper ->
                    this.response = flowOf(resultWrapper)

                    resultWrapperData(resultWrapper, { wizard ->
                        localRepository.insertWizards(
                            wizard = wizard
                        ).collect()
                    }, {
                        localRepository.getWizards()
                    })
                }
            }, onQueryDbError = {
                response
            }
        )
}