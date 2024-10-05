package com.assignment.hometab.domain.wizard.usecases

import com.assignment.core.bases.BaseUseCase
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.wizard.model.WizardWithFavorite
import com.assignment.hometab.domain.wizard.repository.IWizardLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteListUseCase @Inject constructor(
    private val localRepository: IWizardLocalRepository
) : BaseUseCase<Map<String, String>, Flow<ResultWrapper<List<WizardWithFavorite?>?>>> {

    override suspend fun invoke(params: Map<String, String>?): Flow<ResultWrapper<List<WizardWithFavorite>?>> =
        localRepository.getFavorites()
}