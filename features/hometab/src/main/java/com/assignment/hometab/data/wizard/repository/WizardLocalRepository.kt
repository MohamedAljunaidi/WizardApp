package com.assignment.hometab.data.wizard.repository

import com.assignment.caching.extensions.tryMapperQuery
import com.assignment.caching.manager.CachingManager
import com.assignment.caching.manager.ProviderEnum
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.data.mapper.toWizard
import com.assignment.hometab.data.mapper.toWizardEntity
import com.assignment.hometab.domain.home.model.Wizard
import com.assignment.hometab.domain.home.repository.IWizardLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WizardLocalRepository(private val cachingManager: CachingManager) :
    IWizardLocalRepository {

    override fun getWizards(): Flow<ResultWrapper<List<Wizard>?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getWizardList()
        })
        { weather ->
            weather?.toWizard()
        }
        emit(result)
    }


    override fun insertWizards(wizard: List<Wizard>?): Flow<ResultWrapper<Unit?>> =
        flow {
            val result = tryMapperQuery({
                wizard?.toWizardEntity()?.let {
                    cachingManager.getProvider(ProviderEnum.ROOM)
                        .insertWizards(it)
                }
            }) {}
            emit(result)
        }


}
