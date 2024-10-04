package com.assignment.hometab.data.wizard.mapper

import com.assignment.caching.roomdb.features.wizard.entities.WizardEntity
import com.assignment.caching.roomdb.features.wizarddetails.entities.WizardDetailsEntity
import com.assignment.hometab.data.wizard.model.WizardDetailsResponse
import com.assignment.hometab.data.wizard.model.WizardsResponse
import com.assignment.hometab.domain.wizard.model.Wizard
import com.assignment.hometab.domain.wizard.model.WizardDetails
import com.assignment.hometab.domain.wizard.model.WizardDetails.Elixir

internal fun List<WizardsResponse>.toWizards(): List<Wizard> {
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


internal fun List<WizardEntity>.toWizard(): List<Wizard> {
    val dataList = arrayListOf<Wizard>()

    this.map {
        dataList.add(
            Wizard(
                id = it.id,
                firstName = it.firstName ?: "",
                lastName = it.lastName ?: "",
                elixirsCount = it.elixirsCount ?: "",
            )
        )
    }
    return dataList
}

internal fun List<Wizard>.toWizardEntity(): List<WizardEntity> {
    val dataList = arrayListOf<WizardEntity>()


    this.map {
        dataList.add(
            WizardEntity(
                id = it.id,
                firstName = it.firstName ?: "",
                lastName = it.lastName ?: "",
                elixirsCount = it.elixirsCount ?: "",
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


internal fun WizardDetailsEntity.toWizardDetails(): WizardDetails {
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
        id = this.id,
        lastName = this.lastName ?: "",
        elixirsCount = this.elixirsCount ?: "",
    )

}


internal fun WizardDetails.toWizardDetailsEntity(): WizardDetailsEntity {
    val dataList = arrayListOf<WizardDetailsEntity.ElixirEntity>()


    this.elixirs?.map {
        dataList.add(
            WizardDetailsEntity.ElixirEntity(
                id = it?.id ?: "",
                name = it?.name ?: "",
            )
        )
    }
    return WizardDetailsEntity(
        elixirs = dataList,
        firstName = this.firstName ?: "",
        id = this.id ?: "",
        lastName = this.lastName ?: "",
        elixirsCount = this.elixirsCount ?: "",
    )
}


