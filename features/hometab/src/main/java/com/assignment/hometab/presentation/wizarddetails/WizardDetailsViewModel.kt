package com.assignment.hometab.presentation.wizarddetails

import com.assignment.core.bases.BaseViewModel
import com.assignment.core.bases.BaseViewState
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.wizard.model.WizardDetails
import com.assignment.hometab.domain.wizard.usecases.GetWizardDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class WizardDetailsViewModel @Inject constructor(
    private val getWizardDetailsUseCase: GetWizardDetailsUseCase
) : BaseViewModel() {

    private var _wizardDetailsSuccess: MutableStateFlow<WizardDetails?> =
        MutableStateFlow(null)

    val wizardDetailsSuccess: StateFlow<WizardDetails?> =
        _wizardDetailsSuccess.asStateFlow()


    fun getWizardDetails(wizardId: String) {

        launchCoroutine(coroutineExceptionHandler) {
            getWizardDetailsUseCase(wizardId)
                .onStart {
                    _state.emit(BaseViewState.Loading)
                }

                .collectLatest { result ->
                    _state.emit(BaseViewState.DataLoaded)

                    when (result) {

                        is ResultWrapper.Success -> {
                            _wizardDetailsSuccess.emit(result.data)
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