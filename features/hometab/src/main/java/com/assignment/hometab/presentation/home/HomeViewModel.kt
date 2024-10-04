package com.assignment.hometab.presentation.home

import com.assignment.core.bases.BaseViewModel
import com.assignment.core.bases.BaseViewState
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.home.model.Wizard
import com.assignment.hometab.domain.home.usecases.GetWizardListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWizardListUseCase: GetWizardListUseCase
) : BaseViewModel() {

    private var _wizardSuccess: MutableStateFlow<List<Wizard>?> =
        MutableStateFlow(null)

    val wizardSuccess: StateFlow<List<Wizard>?> =
        _wizardSuccess.asStateFlow()


    init {
        getWizardList()
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