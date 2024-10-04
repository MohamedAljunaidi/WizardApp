package com.assignment.hometab.data.wizard.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

class WizardsResponse(
    @SerializedName("elixirs")
    var elixirs: List<Elixir?>? = listOf(),
    @SerializedName("firstName")
    var firstName: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("lastName")
    var lastName: String? = ""
) {
    @Keep
    data class Elixir(
        @SerializedName("id")
        var id: String? = "",
        @SerializedName("name")
        var name: String? = ""
    )
}
