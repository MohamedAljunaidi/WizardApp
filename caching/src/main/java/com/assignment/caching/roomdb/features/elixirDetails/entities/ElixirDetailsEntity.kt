package com.assignment.caching.roomdb.features.elixirDetails.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.assignment.caching.roomdb.features.elixirDetails.converter.IngredientConverter
import com.assignment.caching.roomdb.features.elixirDetails.converter.InventorConverter
import com.google.gson.annotations.SerializedName

@Entity
data class ElixirDetailsEntity(
    @SerializedName("characteristics")
    var characteristics: String? = "",

    @SerializedName("difficulty")
    var difficulty: String? = "",

    @SerializedName("effect")
    var effect: String? = "",

    @SerializedName("id")
    @PrimaryKey
    var id: String,

    @SerializedName("ingredients")
    @field:TypeConverters(IngredientConverter::class)
    var ingredientEntities: List<IngredientEntity?>? = listOf(),

    @SerializedName("inventors")
    @field:TypeConverters(InventorConverter::class)
    var inventorEntities: List<InventorEntity?>? = listOf(),

    @SerializedName("manufacturer")
    var manufacturer: String? = "",

    @SerializedName("name")
    var name: String? = "",

    @SerializedName("sideEffects")
    var sideEffects: String? = "",

    @SerializedName("time")
    var time: String? = ""

) {
    data class IngredientEntity(
        @SerializedName("id")
        var id: String? = "",

        @SerializedName("name")
        var name: String? = ""

    )

    data class InventorEntity(
        @SerializedName("firstName")
        var firstName: String? = "",

        @SerializedName("id")
        var id: String? = "",

        @SerializedName("lastName")
        var lastName: String? = ""
    )
}