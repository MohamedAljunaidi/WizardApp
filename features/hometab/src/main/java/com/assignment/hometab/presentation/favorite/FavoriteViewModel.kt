package com.assignment.hometab.presentation.favorite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.assignment.core.bases.BaseViewModel
import com.assignment.core.bases.BaseViewState
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.wizard.model.Favorite
import com.assignment.hometab.domain.wizard.model.WizardWithFavorite
import com.assignment.hometab.domain.wizard.usecases.GetFavoriteListUseCase
import com.assignment.hometab.domain.wizard.usecases.UpdateFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteListUseCase: GetFavoriteListUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
) :
    BaseViewModel() {

    private var _wizardSuccess: MutableStateFlow<List<WizardWithFavorite>?> =
        MutableStateFlow(null)

    private val _filteredFavorite = mutableStateOf<List<WizardWithFavorite>>(emptyList())
    val filteredFavorite: State<List<WizardWithFavorite>> = _filteredFavorite

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        filterWizards()
    }

    private fun filterWizards() {
        val query = _searchQuery.value.lowercase()
        _filteredFavorite.value = _wizardSuccess.value?.filter { item ->
            item.wizard.firstName?.contains(query, ignoreCase = true) == true ||
                    item.wizard.lastName?.contains(query, ignoreCase = true) == true
        } ?: emptyList()
    }

    fun getFavoriteList() {

        launchCoroutine(coroutineExceptionHandler) {
            getFavoriteListUseCase()
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
                            getFavoriteList()
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