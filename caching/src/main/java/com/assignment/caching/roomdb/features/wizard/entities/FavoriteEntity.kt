package com.assignment.caching.roomdb.features.wizard.entities


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "favorite_table",
    foreignKeys = [ForeignKey(
        entity = WizardEntity::class,
        parentColumns = ["id"],
        childColumns = ["wizardId"],
        onDelete = ForeignKey.NO_ACTION
    )]
)
data  class FavoriteEntity(

    @PrimaryKey
    var wizardId: String,

    @SerializedName("isFavorite")
    val isFavorite: Boolean = true
)
