package com.assignment.elixirs.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ElixirDetailsResponse(
    @SerializedName("characteristics")
    var characteristics: String? = "",
    @SerializedName("difficulty")
    var difficulty: String? = "",
    @SerializedName("effect")
    var effect: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("ingredients")
    var ingredientResponses: List<IngredientResponse?>? = listOf(),
    @SerializedName("inventors")
    var inventorResponses: List<InventorResponse?>? = listOf(),
    @SerializedName("manufacturer")
    var manufacturer: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("sideEffects")
    var sideEffects: String? = "",
    @SerializedName("time")
    var time: String? = ""
) {
    @Keep
    data class IngredientResponse(
        @SerializedName("id")
        var id: String? = "",
        @SerializedName("name")
        var name: String? = ""
    )

    @Keep
    data class InventorResponse(
        @SerializedName("firstName")
        var firstName: String? = "",
        @SerializedName("id")
        var id: String? = "",
        @SerializedName("lastName")
        var lastName: String? = ""
    )
}