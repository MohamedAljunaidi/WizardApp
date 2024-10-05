package com.assignment.caching.roomdb.features.wizarddetails.converter

import com.assignment.caching.extensions.type
import com.assignment.caching.roomdb.common.converter.ListConverter
import com.assignment.caching.roomdb.features.wizarddetails.entities.WizardDetailsEntity


class ElixirListConverter :
    ListConverter<WizardDetailsEntity.ElixirEntity>(type<List<WizardDetailsEntity.ElixirEntity>>())
