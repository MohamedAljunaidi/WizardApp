package com.assignment.caching.roomdb.features.elixirDetails.dao

import androidx.room.Dao
import androidx.room.Query
import com.assignment.caching.roomdb.common.BaseDao
import com.assignment.caching.roomdb.features.elixirDetails.entities.ElixirDetailsEntity

@Dao
interface ElixirDetailsDao : BaseDao<ElixirDetailsEntity> {

    @Query("SELECT * FROM ElixirDetailsEntity WHERE id = :elixirId")
    fun getElixirDetailsEntity(elixirId: String): ElixirDetailsEntity? {
        val result = actualElixirDetailsEntity(elixirId)
        return if (result.id.isEmpty()) {
            null
        } else {
            result
        }
    }

    @Query("SELECT * FROM ElixirDetailsEntity WHERE id = :elixirId")
    fun actualElixirDetailsEntity(elixirId: String): ElixirDetailsEntity


    @Query("DELETE FROM ElixirDetailsEntity")
    fun deleteAllData()

}
