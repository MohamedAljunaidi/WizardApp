package com.assignment.elixirs.presentation

import com.assignment.core.bases.BaseViewModel
import com.assignment.core.bases.BaseViewState
import com.assignment.core.model.ResultWrapper
import com.assignment.elixirs.domain.model.ElixirDetails
import com.assignment.elixirs.domain.usecases.GetElixirDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ElixirsDetailsViewModel @Inject constructor(
    private val getElixirDetailsUseCase: GetElixirDetailsUseCase
) : BaseViewModel() {

    private var _elixirDetailsSuccess: MutableStateFlow<ElixirDetails?> =
        MutableStateFlow(null)

    val elixirDetailsSuccess: StateFlow<ElixirDetails?> =
        _elixirDetailsSuccess.asStateFlow()

    fun getElixirDetails(elixirId: String) {

        launchCoroutine(coroutineExceptionHandler) {
            getElixirDetailsUseCase(elixirId)
                .onStart {
                    _state.emit(BaseViewState.Loading)
                }

                .collectLatest { result ->
                    _state.emit(BaseViewState.DataLoaded)

                    when (result) {

                        is ResultWrapper.Success -> {
                            _elixirDetailsSuccess.emit(result.data)
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