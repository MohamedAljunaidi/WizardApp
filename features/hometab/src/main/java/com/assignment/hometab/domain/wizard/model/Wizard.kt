package com.assignment.hometab.domain.wizard.model

import androidx.annotation.Keep

@Keep
data class Wizard(

    var id: String,
    var firstName: String? = "",
    var lastName: String? = "",
    var elixirsCount: String? = ""
)
