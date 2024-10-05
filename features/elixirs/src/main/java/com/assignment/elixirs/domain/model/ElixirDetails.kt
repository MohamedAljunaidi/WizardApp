package com.assignment.elixirs.domain.model

import androidx.annotation.Keep

@Keep
data class ElixirDetails(
    var characteristics: String? = "",
    var difficulty: String? = "",
    var effect: String? = "",
    var id: String? = "",
    var ingredients: List<Ingredient?>? = listOf(),
    var inventors: List<Inventor?>? = listOf(),
    var manufacturer: String? = "",
    var name: String? = "",
    var sideEffects: String? = "",
    var time: String? = ""
) {
    @Keep
    data class Ingredient(
        var id: String? = "",
        var name: String? = ""
    )

    @Keep
    data class Inventor(
        var firstName: String? = "",
        var id: String? = "",
        var lastName: String? = ""
    )
}