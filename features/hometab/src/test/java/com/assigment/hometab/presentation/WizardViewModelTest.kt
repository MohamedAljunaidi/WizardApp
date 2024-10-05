package com.assigment.hometab.presentation

import app.cash.turbine.test
import com.assigment.hometab.common.TestCoroutineRule
import com.assignment.core.model.ErrorModel
import com.assignment.core.model.ResultException
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.domain.wizard.model.Favorite
import com.assignment.hometab.domain.wizard.model.Wizard
import com.assignment.hometab.domain.wizard.model.WizardWithFavorite
import com.assignment.hometab.domain.wizard.usecases.GetWizardListUseCase
import com.assignment.hometab.domain.wizard.usecases.UpdateFavoriteUseCase
import com.assignment.hometab.presentation.wizard.WizardViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import java.util.ArrayList

private val WIZARD_LIST: ArrayList<WizardWithFavorite> = arrayListOf(
    WizardWithFavorite(
        wizard = Wizard(),
        favorite = Favorite()
    )
)
private val SUCCESS_WIZARD_LIST = ResultWrapper.Success(WIZARD_LIST)
private val ERROR_WIZARD_LIST =
    ResultWrapper.Error(ResultException(errorModel = ErrorModel(errorDescription = "error")))
private const val EXCEPTION = "EXCEPTION"


@RunWith(MockitoJUnitRunner::class)
class WizardViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var sut: WizardViewModel

    @Mock
    private lateinit var getWizardListUseCase: GetWizardListUseCase

    @Mock
    private lateinit var updateFavoriteUseCase: UpdateFavoriteUseCase

    @Before
    fun setUp() {
        sut = WizardViewModel(getWizardListUseCase, updateFavoriteUseCase)
    }

    @Test
    fun `test getWizardList when api success, wizardListSuccess should emit correct data`() =
        runTest {
            prepareSuccess()
            sut.getWizardList()
            getWizardListUseCase().test {
                assertEquals(SUCCESS_WIZARD_LIST, awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `test getWizardList when called, getWizardList Should Not Interactions`() =
        runTest {
            prepareSuccess()
            sut.getWizardList()
            verifyNoMoreInteractions(getWizardListUseCase)
        }


    @Test
    fun `test getWizardList when called, getWizardList Should Called Once`() =
        runTest {
            getWizardListUseCase()
            verify(getWizardListUseCase, Mockito.times(1))()
        }

    @Test
    fun `test getWizardList when api error, state should emit error data`() = runTest {
        prepareFailure()
        sut.getWizardList()
        getWizardListUseCase().test {
            assertEquals(ERROR_WIZARD_LIST, awaitItem())
            awaitComplete()
        }
    }


    @Test
    fun `test getWizardList when api get exception, state should emit exception data`() = runTest {
        prepareCoroutineException()
        sut.getWizardList()
        getWizardListUseCase().test {
            assertEquals(EXCEPTION, awaitError().message)
        }
    }

    @Test
    fun `test updateFavorite when success, wizardList should be reloaded`() = runTest {
        prepareSuccess()
        prepareUpdateFavoriteSuccess()

        sut.updateFavorite(true, "wizardId")

        updateFavoriteUseCase(Favorite("wizardId", true)).test {
            assertEquals(ResultWrapper.Success(Unit), awaitItem())
            awaitComplete()

        }

        // Verify getWizardList was called to refresh the wizard list after updating the favorite status
        getWizardListUseCase()
        verify(getWizardListUseCase, Mockito.times(1)).invoke()

    }

    @Test
    fun `test updateFavorite when error, state should emit error`() = runTest {
        prepareUpdateFavoriteFailure()

        sut.updateFavorite(true, "wizardId")

        updateFavoriteUseCase(Favorite("wizardId", true)).test {
            assertEquals(ERROR_WIZARD_LIST, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `test updateFavorite when exception, state should emit exception`() = runTest {
        prepareUpdateFavoriteException()

        sut.updateFavorite(true, "wizardId")

        updateFavoriteUseCase(Favorite("wizardId", true)).test {
            assertEquals(EXCEPTION, awaitError().message)
        }
    }
    private suspend fun prepareCoroutineException() {
        Mockito.`when`(getWizardListUseCase())
            .thenReturn(flow { throw IllegalArgumentException(EXCEPTION) })
    }

    private suspend fun prepareSuccess() {
        Mockito.`when`(getWizardListUseCase())
            .thenReturn(flowOf(SUCCESS_WIZARD_LIST))
    }
    private suspend fun prepareFailure() {
        Mockito.`when`(getWizardListUseCase())
            .thenReturn(flowOf(ERROR_WIZARD_LIST))
    }

    private suspend fun prepareUpdateFavoriteSuccess() {
        Mockito.`when`(updateFavoriteUseCase(Favorite("wizardId", true)))
            .thenReturn(flowOf(ResultWrapper.Success(Unit)))
    }

    private suspend fun prepareUpdateFavoriteFailure() {
        Mockito.`when`(updateFavoriteUseCase(Favorite("wizardId", true)))
            .thenReturn(flowOf(ERROR_WIZARD_LIST))
    }

    private suspend fun prepareUpdateFavoriteException() {
        Mockito.`when`(updateFavoriteUseCase(Favorite("wizardId", true)))
            .thenReturn(flow { throw IllegalArgumentException(EXCEPTION) })
    }
}