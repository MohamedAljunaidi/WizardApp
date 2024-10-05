package com.assignment.caching.roomdb.features.elixirDetails.converter

import com.assignment.caching.extensions.type
import com.assignment.caching.roomdb.common.converter.ListConverter
import com.assignment.caching.roomdb.features.elixirDetails.entities.ElixirDetailsEntity


class IngredientConverter :
    ListConverter<ElixirDetailsEntity.IngredientEntity>(type<List<ElixirDetailsEntity.IngredientEntity>>())
