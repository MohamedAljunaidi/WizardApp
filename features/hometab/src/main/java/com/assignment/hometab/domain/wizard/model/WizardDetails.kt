package com.assignment.hometab.domain.wizard.model


import androidx.annotation.Keep

@Keep
data class WizardDetails(
    var elixirs: List<Elixir?>? = listOf(),
    var firstName: String? = "",
    var id: String? = "",
    var lastName: String? = "",
    var elixirsCount: String? = ""
) {
    @Keep
    data class Elixir(
        var id: String? = "",
        var name: String? = ""
    )
}