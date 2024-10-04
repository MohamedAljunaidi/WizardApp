package com.assignment.caching.roomdb.features.wizard.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class WizardEntity(

    @SerializedName("id")
    @PrimaryKey
    var id: String,

    @SerializedName("firstName")
    var firstName: String? = "",

    @SerializedName("lastName")
    var lastName: String? = "",

    @SerializedName("elixirsCount")
    var elixirsCount: String? = ""
)
