package com.assignment.caching.roomdb.common

import com.assignment.caching.roomdb.features.event.entities.WizardEntity
import com.assignment.core.model.ResultWrapper

interface IRoomManager {

    suspend fun insertWizards(wizardEntity: List<WizardEntity>): ResultWrapper<Unit>? = null

    suspend fun getWizardList(): ResultWrapper<List<WizardEntity>>? = null
    suspend fun deleteWizards(): ResultWrapper<Unit>? = null

}