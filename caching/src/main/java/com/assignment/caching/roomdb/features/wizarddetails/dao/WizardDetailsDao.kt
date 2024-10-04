package com.assignment.caching.roomdb.features.wizarddetails.dao

import androidx.room.Dao
import androidx.room.Query
import com.assignment.caching.roomdb.common.BaseDao
import com.assignment.caching.roomdb.features.wizarddetails.entities.WizardDetailsEntity

@Dao
interface WizardDetailsDao: BaseDao<WizardDetailsEntity> {



    @Query("SELECT * FROM WizardDetailsEntity WHERE id = :wizardId")
    fun getWizardDetailsEntity(wizardId:String): WizardDetailsEntity? {
        val result = actualWizardDetailsEntity(wizardId)
        return if (result.id.isEmpty()){
            null
        }else{
            result
        }
    }

    @Query("SELECT * FROM WizardDetailsEntity WHERE id = :wizardId")
    fun actualWizardDetailsEntity(wizardId:String): WizardDetailsEntity



    @Query("DELETE FROM WizardDetailsEntity")
    fun deleteAllData()

}
