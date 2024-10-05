package com.assignment.hometab.presentation.wizard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.assignment.core.bases.BaseViewModel
import com.assignment.core.bases.BaseViewState
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.wizard.model.Favorite
import com.assignment.hometab.domain.wizard.model.WizardWithFavorite
import com.assignment.hometab.domain.wizard.usecases.GetWizardListUseCase
import com.assignment.hometab.domain.wizard.usecases.UpdateFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class WizardViewModel @Inject constructor(
    private val getWizardListUseCase: GetWizardListUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
) : BaseViewModel() {

    private var _wizardSuccess: MutableStateFlow<List<WizardWithFavorite>?> =
        MutableStateFlow(null)

    private val _filteredWizards = mutableStateOf<List<WizardWithFavorite>>(emptyList())
    val filteredWizards: State<List<WizardWithFavorite>> = _filteredWizards

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    init {
        getWizardList()
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        filterWizards()
    }

    private fun filterWizards() {
        val query = _searchQuery.value.lowercase()
        _filteredWizards.value = _wizardSuccess.value?.filter { item ->
            item.wizard.firstName?.contains(query, ignoreCase = true) == true ||
                    item.wizard.lastName?.contains(query, ignoreCase = true) == true
        } ?: emptyList()
    }

    fun getWizardList() {

        launchCoroutine(coroutineExceptionHandler) {
            getWizardListUseCase()
                .onStart {
                    _state.emit(BaseViewState.Loading)
                }

                .collectLatest { result ->
                    _state.emit(BaseViewState.DataLoaded)

                    when (result) {

                        is ResultWrapper.Success -> {
                            _wizardSuccess.emit(result.data)
                            filterWizards()
                        }

                        is ResultWrapper.Error -> {
                            _state.emit(
                                BaseViewState.Error(
                                    result.error
                                )
                            )

                        }

                    }
                }
        }
    }

    fun updateFavorite(isFavorite: Boolean, wizardId: String) {


        launchCoroutine(coroutineExceptionHandler) {
            updateFavoriteUseCase(Favorite(wizardId, isFavorite))
                .onStart {
                    _state.emit(BaseViewState.Loading)
                }

                .collectLatest { result ->
                    _state.emit(BaseViewState.DataLoaded)

                    when (result) {

                        is ResultWrapper.Success -> {
                            getWizardList()
                        }

                        is ResultWrapper.Error -> {
                            _state.emit(
                                BaseViewState.Error(
                                    result.error
                                )
                            )

                        }

                    }
                }
        }
    }


}