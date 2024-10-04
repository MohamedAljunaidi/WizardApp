package com.assignment.caching.roomdb.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.caching.roomdb.features.event.dao.WizardDao
import com.assignment.caching.roomdb.features.event.entities.WizardEntity

@Database(
    entities = [
        WizardEntity::class
    ], version = RoomConstants.DATABASE_VERSION
)
abstract class DatabaseRoom : RoomDatabase() {
    abstract fun wizardDao(): WizardDao

}
