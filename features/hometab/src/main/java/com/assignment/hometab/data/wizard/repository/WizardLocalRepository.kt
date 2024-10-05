package com.assignment.hometab.data.wizard.repository

import com.assignment.caching.extensions.tryMapperQuery
import com.assignment.caching.manager.CachingManager
import com.assignment.caching.manager.ProviderEnum
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.data.wizard.mapper.toFavoriteEntity
import com.assignment.hometab.data.wizard.mapper.toWizardDetails
import com.assignment.hometab.data.wizard.mapper.toWizardDetailsEntity
import com.assignment.hometab.data.wizard.mapper.toWizardEntity
import com.assignment.hometab.data.wizard.mapper.entityToWizardWithFavoriteList
import com.assignment.hometab.domain.wizard.model.Favorite
import com.assignment.hometab.domain.wizard.model.Wizard
import com.assignment.hometab.domain.wizard.model.WizardDetails
import com.assignment.hometab.domain.wizard.model.WizardWithFavorite
import com.assignment.hometab.domain.wizard.repository.IWizardLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WizardLocalRepository(private val cachingManager: CachingManager) :
    IWizardLocalRepository {

    override fun getWizardWithFavorite(): Flow<ResultWrapper<List<WizardWithFavorite>?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getWizardWithFavorite()
        })
        { weather ->
            weather?.entityToWizardWithFavoriteList()
        }
        emit(result)
    }

    override fun getFavorites(): Flow<ResultWrapper<List<WizardWithFavorite>?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getFavorites()
        })
        { weather ->
            weather?.entityToWizardWithFavoriteList()
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
    override fun insertFavorite(favorite: Favorite): Flow<ResultWrapper<Unit?>> =
        flow {
            val result = tryMapperQuery({
                    cachingManager.getProvider(ProviderEnum.ROOM)
                        .insertFavorite(favorite.toFavoriteEntity())
            }) {}
            emit(result)
        }

    override fun getWizardDetails(wizardId: String): Flow<ResultWrapper<WizardDetails?>> = flow {
        val result = tryMapperQuery({
            cachingManager.getProvider(ProviderEnum.ROOM).getWizardDetails(wizardId)
        })
        { weather ->
            weather?.toWizardDetails()
        }
        emit(result)
    }


    override fun insertWizardDetails(wizardDetails: WizardDetails?): Flow<ResultWrapper<Unit?>> =
        flow {
            val result = tryMapperQuery({
                wizardDetails?.toWizardDetailsEntity()?.let {
                    cachingManager.getProvider(ProviderEnum.ROOM)
                        .insertWizardDetails(it)
                }
            }) {}
            emit(result)
        }


}
