package com.assignment.caching.roomdb.features.wizarddetails.entities


import com.google.gson.annotations.SerializedName
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.assignment.caching.roomdb.features.wizarddetails.ElixirListConverter

@Entity
data class WizardDetailsEntity(

    @SerializedName("elixirs")
    @field:TypeConverters(ElixirListConverter::class)
    var elixirs: List<ElixirEntity?>? = listOf(),

    @SerializedName("firstName")
    var firstName: String? = "",

    @SerializedName("id")
    @PrimaryKey
    var id: String,

    @SerializedName("lastName")
    var lastName: String? = "",

    @SerializedName("elixirsCount")
    var elixirsCount: String? = ""


) {
    data class ElixirEntity(

        @SerializedName("id")
        var id: String? = "",

        @SerializedName("name")
        var name: String? = ""
    )
}