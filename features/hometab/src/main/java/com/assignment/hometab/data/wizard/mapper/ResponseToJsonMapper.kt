package com.assignment.hometab.data.wizard.mapper

import com.assignment.hometab.data.wizard.model.WizardDetailsResponse
import com.assignment.hometab.data.wizard.model.WizardsResponse
import com.assignment.hometab.domain.wizard.model.Wizard
import com.assignment.hometab.domain.wizard.model.WizardDetails
import com.assignment.hometab.domain.wizard.model.WizardDetails.Elixir
import com.assignment.hometab.domain.wizard.model.WizardWithFavorite

internal fun List<WizardsResponse>.toWizardList(): List<Wizard> {
    val dataList = arrayListOf<Wizard>()
    this.map {
        dataList.add(
            Wizard(
                id = it.id ?: "",
                firstName = it.firstName ?: "",
                lastName = it.lastName ?: "",
                elixirsCount = (it.elixirs?.count() ?: "0").toString(),
            )
        )
    }
    return dataList
}

internal fun List<Wizard>.toWizardWithFavoriteList(): List<WizardWithFavorite> {
    val dataList = arrayListOf<WizardWithFavorite>()
    this.map {
        dataList.add(
            WizardWithFavorite(
                wizard = it,
            )
        )
    }
    return dataList
}


internal fun WizardDetailsResponse.toWizardDetails(): WizardDetails {
    val dataList = arrayListOf<Elixir>()
    this.elixirs?.map {
        dataList.add(
            Elixir(
                id = it?.id ?: "",
                name = it?.name ?: "",
            )
        )
    }

    return WizardDetails(
        elixirs = dataList,
        firstName = this.firstName ?: "",
        id = this.id ?: "",
        lastName = this.lastName ?: "",
        elixirsCount = (this.elixirs?.count() ?: "0").toString(),
    )
}



