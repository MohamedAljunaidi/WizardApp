package com.assignment.caching.roomdb.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.caching.roomdb.features.wizard.dao.WizardDao
import com.assignment.caching.roomdb.features.wizard.entities.WizardEntity
import com.assignment.caching.roomdb.features.wizarddetails.dao.WizardDetailsDao
import com.assignment.caching.roomdb.features.wizarddetails.entities.WizardDetailsEntity

@Database(
    entities = [
        WizardEntity::class,
        WizardDetailsEntity::class
    ], version = RoomConstants.DATABASE_VERSION
)
abstract class DatabaseRoom : RoomDatabase() {
    abstract fun wizardDao(): WizardDao
    abstract fun wizardDetailsDao(): WizardDetailsDao

}
