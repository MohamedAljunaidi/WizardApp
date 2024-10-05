package com.assignment.hometab.domain.wizard.usecases

import com.assignment.core.bases.BaseUseCase
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.wizard.model.Favorite
import com.assignment.hometab.domain.wizard.repository.IWizardLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(
    private val localRepository: IWizardLocalRepository
) :
    BaseUseCase<Favorite, Flow<ResultWrapper<Unit?>>> {

    override suspend fun invoke(params: Favorite?): Flow<ResultWrapper<Unit?>> =
        localRepository.insertFavorite(
            favorite = params as Favorite
        )
}