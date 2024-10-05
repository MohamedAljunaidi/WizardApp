package com.assignment.hometab.data.wizard.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class WizardDetailsResponse(
    @SerializedName("elixirs")
    var elixirs: List<ElixirResponse?>? = listOf(),
    @SerializedName("firstName")
    var firstName: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("lastName")
    var lastName: String? = ""
) {
    @Keep
    data class ElixirResponse(
        @SerializedName("id")
        var id: String? = "",
        @SerializedName("name")
        var name: String? = ""
    )
}