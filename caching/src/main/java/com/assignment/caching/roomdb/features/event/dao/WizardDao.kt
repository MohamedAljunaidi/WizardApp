package com.assignment.caching.roomdb.features.event.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.caching.roomdb.common.BaseDao
import com.assignment.caching.roomdb.features.event.entities.WizardEntity

@Dao
interface WizardDao: BaseDao<WizardEntity> {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWizard(obj: List<WizardEntity>)

    @Query("SELECT * FROM WizardEntity")
    fun getWizardEntity(): List<WizardEntity>

    @Query("DELETE FROM WizardEntity")
    fun deleteAllData()

}
