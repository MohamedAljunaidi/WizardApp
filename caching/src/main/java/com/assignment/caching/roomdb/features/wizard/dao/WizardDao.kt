package com.assignment.caching.roomdb.features.wizard.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.assignment.caching.roomdb.common.BaseDao
import com.assignment.caching.roomdb.features.wizard.entities.FavoriteEntity
import com.assignment.caching.roomdb.features.wizard.entities.WizardEntity
import com.assignment.caching.roomdb.features.wizard.entities.WizardWithFavoriteEntity

@Dao
interface WizardDao: BaseDao<WizardEntity> {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWizard(obj: List<WizardEntity>)

    @Query("SELECT * FROM wizard_table")
    fun actualGetWizardEntity(): List<WizardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Transaction
    @Query("SELECT * FROM wizard_table")
    suspend fun getWizardsWithFavorite(): List<WizardWithFavoriteEntity>

}
