package com.assignment.caching.roomdb.common

import com.assignment.caching.roomdb.features.elixirDetails.entities.ElixirDetailsEntity
import com.assignment.caching.roomdb.features.wizard.entities.FavoriteEntity
import com.assignment.caching.roomdb.features.wizard.entities.WizardEntity
import com.assignment.caching.roomdb.features.wizard.entities.WizardWithFavoriteEntity
import com.assignment.caching.roomdb.features.wizarddetails.entities.WizardDetailsEntity
import com.assignment.core.model.ResultWrapper

interface IRoomManager {

    suspend fun insertWizards(wizardEntity: List<WizardEntity>): ResultWrapper<Unit>? = null
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity): ResultWrapper<Unit>? = null

    suspend fun getWizardWithFavorite(): ResultWrapper<List<WizardWithFavoriteEntity>?>? = null
    suspend fun getFavorites(): ResultWrapper<List<WizardWithFavoriteEntity>?>? = null

    suspend fun insertWizardDetails(wizardDetailsEntity: WizardDetailsEntity): ResultWrapper<Unit>? =
        null

    suspend fun getWizardDetails(wizardId: String): ResultWrapper<WizardDetailsEntity>? = null


    suspend fun insertElixirDetails(elixirDetailsEntity: ElixirDetailsEntity): ResultWrapper<Unit>? =
        null

    suspend fun getElixirDetails(elixirId: String): ResultWrapper<ElixirDetailsEntity>? = null


}