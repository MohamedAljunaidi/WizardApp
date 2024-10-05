package com.assignment.elixirs.data.mapper

import com.assignment.caching.roomdb.features.elixirDetails.entities.ElixirDetailsEntity
import com.assignment.elixirs.data.model.ElixirDetailsResponse
import com.assignment.elixirs.domain.model.ElixirDetails


internal fun ElixirDetailsResponse.toElixirDetails(): ElixirDetails {
    val inventorList = arrayListOf<ElixirDetails.Inventor>()
    val ingredientList = arrayListOf<ElixirDetails.Ingredient>()
    this.inventorResponses?.map {
        inventorList.add(
            ElixirDetails.Inventor(
                id = it?.id ?: "",
                firstName = it?.firstName ?: "",
                lastName = it?.lastName ?: "",
            )
        )
    }
    this.ingredientResponses?.map {
        ingredientList.add(
            ElixirDetails.Ingredient(
                id = it?.id ?: "",
                name = it?.name ?: "",
            )
        )
    }

    return ElixirDetails(
        characteristics = this.characteristics ?: "",
        difficulty = this.difficulty ?: "",
        effect = this.effect ?: "",
        id = this.id ?: "",
        ingredients = ingredientList,
        inventors = inventorList,
        manufacturer = this.manufacturer ?: "",
        name = this.name ?: "",
        sideEffects = this.sideEffects ?: "",
        time = this.time ?: "",
    )
}


internal fun ElixirDetailsEntity.toElixirDetails(): ElixirDetails {

    val inventorList = arrayListOf<ElixirDetails.Inventor>()
    val ingredientList = arrayListOf<ElixirDetails.Ingredient>()
    this.inventorEntities?.map {
        inventorList.add(
            ElixirDetails.Inventor(
                id = it?.id ?: "",
                firstName = it?.firstName ?: "",
                lastName = it?.lastName ?: "",
            )
        )
    }
    this.ingredientEntities?.map {
        ingredientList.add(
            ElixirDetails.Ingredient(
                id = it?.id ?: "",
                name = it?.name ?: "",
            )
        )
    }

    return ElixirDetails(
        characteristics = this.characteristics ?: "",
        difficulty = this.difficulty ?: "",
        effect = this.effect ?: "",
        id = this.id,
        ingredients = ingredientList,
        inventors = inventorList,
        manufacturer = this.manufacturer ?: "",
        name = this.name ?: "",
        sideEffects = this.sideEffects ?: "",
        time = this.time ?: "",
    )

}


internal fun ElixirDetails.toElixirDetailsEntity(): ElixirDetailsEntity {
    val inventorList = arrayListOf<ElixirDetailsEntity.InventorEntity>()
    val ingredientList = arrayListOf<ElixirDetailsEntity.IngredientEntity>()
    this.inventors?.map {
        inventorList.add(
            ElixirDetailsEntity.InventorEntity(
                id = it?.id ?: "",
                firstName = it?.firstName ?: "",
                lastName = it?.lastName ?: "",
            )
        )
    }
    this.ingredients?.map {
        ingredientList.add(
            ElixirDetailsEntity.IngredientEntity(
                id = it?.id ?: "",
                name = it?.name ?: "",
            )
        )
    }

    return ElixirDetailsEntity(
        characteristics = this.characteristics ?: "",
        difficulty = this.difficulty ?: "",
        effect = this.effect ?: "",
        ingredientEntities = ingredientList,
        inventorEntities = inventorList,
        id = this.id ?: "",
        manufacturer = this.manufacturer ?: "",
        name = this.name ?: "",
        sideEffects = this.sideEffects ?: "",
        time = this.time ?: "",
    )
}

