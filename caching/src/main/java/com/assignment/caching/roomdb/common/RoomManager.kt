package com.assignment.caching.roomdb.common

import com.assignment.caching.extensions.safeLocalDataCall
import com.assignment.caching.manager.BaseManager
import com.assignment.caching.roomdb.features.elixirDetails.entities.ElixirDetailsEntity
import com.assignment.caching.roomdb.features.wizard.entities.FavoriteEntity
import com.assignment.caching.roomdb.features.wizard.entities.WizardEntity
import com.assignment.caching.roomdb.features.wizarddetails.entities.WizardDetailsEntity
import com.assignment.core.model.ResultWrapper
import javax.inject.Inject

class RoomManager @Inject constructor(private val databaseRoom: DatabaseRoom) : BaseManager {

    override suspend fun insertWizards(wizardEntity: List<WizardEntity>): ResultWrapper<Unit> =
        safeLocalDataCall { databaseRoom.wizardDao().insertWizard(wizardEntity) }

    override suspend fun insertFavorite(favoriteEntity: FavoriteEntity): ResultWrapper<Unit> =
        safeLocalDataCall { databaseRoom.wizardDao().insertFavorite(favoriteEntity) }


    override suspend fun getWizardWithFavorite() =
        safeLocalDataCall {
            databaseRoom.wizardDao().getWizardsWithFavorite()
        }


    override suspend fun insertWizardDetails(wizardDetailsEntity: WizardDetailsEntity): ResultWrapper<Unit> =
        safeLocalDataCall { databaseRoom.wizardDetailsDao().insert(wizardDetailsEntity) }


    override suspend fun getWizardDetails(wizardId: String) =
        safeLocalDataCall {
            databaseRoom.wizardDetailsDao().getWizardDetailsEntity(wizardId)
        }

    override suspend fun insertElixirDetails(elixirDetailsEntity: ElixirDetailsEntity): ResultWrapper<Unit> =
        safeLocalDataCall { databaseRoom.elixirDetailsDao().insert(elixirDetailsEntity) }


    override suspend fun getElixirDetails(elixirId: String) =
        safeLocalDataCall {
            databaseRoom.elixirDetailsDao().getElixirDetailsEntity(elixirId)
        }


}