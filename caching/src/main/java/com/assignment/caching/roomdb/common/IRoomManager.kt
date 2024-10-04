package com.assignment.caching.roomdb.common

import com.assignment.caching.roomdb.features.wizard.entities.WizardEntity
import com.assignment.caching.roomdb.features.wizarddetails.entities.WizardDetailsEntity
import com.assignment.core.model.ResultWrapper

interface IRoomManager {

    suspend fun insertWizards(wizardEntity: List<WizardEntity>): ResultWrapper<Unit>? = null

    suspend fun getWizardList(): ResultWrapper<List<WizardEntity>?>? = null

    suspend fun insertWizardDetails(wizardDetailsEntity: WizardDetailsEntity): ResultWrapper<Unit>? = null
    suspend fun getWizardDetails(wizardId:String): ResultWrapper<WizardDetailsEntity>? = null


}