package com.assignment.caching.roomdb.features.wizard.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.caching.roomdb.common.BaseDao
import com.assignment.caching.roomdb.features.wizard.entities.WizardEntity

@Dao
interface WizardDao: BaseDao<WizardEntity> {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWizard(obj: List<WizardEntity>)

    @Query("SELECT * FROM WizardEntity")
    fun getWizardEntity(): List<WizardEntity>? {
        val result = actualGetWizardEntity()
        return result.ifEmpty { null }
    }

    @Query("SELECT * FROM WizardEntity")
    fun actualGetWizardEntity(): List<WizardEntity>


    @Query("DELETE FROM WizardEntity")
    fun deleteAllData()

}
