package com.assignment.hometab.data.mapper

import com.assignment.caching.roomdb.features.event.entities.WizardEntity
import com.assignment.hometab.data.wizard.model.WizardsResponse
import com.assignment.hometab.domain.home.model.Wizard

internal fun List<WizardsResponse>.toWizards(): List<Wizard> {
    val dataList = arrayListOf<Wizard>()
    this.map {
        dataList.add(
            Wizard(
                id = it.id ?: "",
                firstName = it.firstName ?: "",
                lastName = it.lastName ?: "",
                elixirsCount = (it.elixirs?.count()?:"0").toString(),
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
                id = it.id ?: "",
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



