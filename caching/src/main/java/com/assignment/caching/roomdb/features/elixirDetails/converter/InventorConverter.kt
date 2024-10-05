package com.assignment.caching.roomdb.features.elixirDetails.converter

import com.assignment.caching.extensions.type
import com.assignment.caching.roomdb.common.converter.ListConverter
import com.assignment.caching.roomdb.features.elixirDetails.entities.ElixirDetailsEntity


class InventorConverter :
    ListConverter<ElixirDetailsEntity.InventorEntity>(type<List<ElixirDetailsEntity.InventorEntity>>())
