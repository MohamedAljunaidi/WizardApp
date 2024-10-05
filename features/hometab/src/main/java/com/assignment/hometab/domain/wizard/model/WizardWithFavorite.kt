package com.assignment.hometab.domain.wizard.model


data class WizardWithFavorite(
     val wizard: Wizard,
    val favorite: Favorite? = null
)