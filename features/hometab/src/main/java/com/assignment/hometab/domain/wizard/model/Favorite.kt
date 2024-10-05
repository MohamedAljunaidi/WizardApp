package com.assignment.hometab.domain.wizard.model

import androidx.annotation.Keep

@Keep
data class Favorite(

    var id: String? = "",
    val isFavorite: Boolean = false
)