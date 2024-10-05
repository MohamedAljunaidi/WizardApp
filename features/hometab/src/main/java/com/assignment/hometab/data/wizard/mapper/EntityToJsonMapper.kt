package com.assignment.hometab.data.wizard.mapper

import com.assignment.caching.roomdb.features.wizard.entities.FavoriteEntity
import com.assignment.caching.roomdb.features.wizard.entities.WizardEntity
import com.assignment.caching.roomdb.features.wizard.entities.WizardWithFavoriteEntity
import com.assignment.caching.roomdb.features.wizarddetails.entities.WizardDetailsEntity
import com.assignment.hometab.domain.wizard.model.Favorite
import com.assignment.hometab.domain.wizard.model.Wizard
import com.assignment.hometab.domain.wizard.model.WizardDetails
import com.assignment.hometab.domain.wizard.model.WizardDetails.Elixir
import com.assignment.hometab.domain.wizard.model.WizardWithFavorite


internal fun List<WizardEntity>.entityToWizardList(): List<Wizard> {
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
                id = it.id ?: "",
                firstName = it.firstName ?: "",
                lastName = it.lastName ?: "",
                elixirsCount = it.elixirsCount ?: "",
            )
        )
    }
    return dataList
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

internal fun Favorite.toFavoriteEntity(): FavoriteEntity {

    return FavoriteEntity(
        wizardId = this.id ?: "",
        isFavorite = this.isFavorite,
    )
}


internal fun List<WizardWithFavoriteEntity>.entityToWizardWithFavoriteList(): List<WizardWithFavorite> {
    val dataList = arrayListOf<WizardWithFavorite>()
    this.map {
        dataList.add(
            WizardWithFavorite(
                wizard = it.wizard.toWizard(),
                favorite = it.favorite?.toFavorite(),
            )
        )
    }
    return dataList
}


internal fun WizardEntity.toWizard(): Wizard {

    return Wizard(
        firstName = this.firstName ?: "",
        id = this.id,
        lastName = this.lastName ?: "",
        elixirsCount = this.elixirsCount ?: "",
    )
}

internal fun FavoriteEntity.toFavorite(): Favorite {

    return Favorite(
        id = this.wizardId,
        isFavorite = this.isFavorite,
    )
}

