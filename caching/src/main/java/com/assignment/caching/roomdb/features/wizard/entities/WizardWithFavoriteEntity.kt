package com.assignment.caching.roomdb.features.wizard.entities

import androidx.room.Embedded
import androidx.room.Relation

data class WizardWithFavoriteEntity(
    @Embedded val wizard: WizardEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "wizardId"
    )
    val favorite: FavoriteEntity?
)