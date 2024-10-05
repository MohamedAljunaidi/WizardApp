package com.assigment.hometab.repository

import com.assigment.hometab.common.TestCoroutineRule
import com.assignment.core.model.ResultWrapper
import com.assignment.exceptions.NetworkException
import com.assignment.hometab.data.wizard.WizardsService
import com.assignment.hometab.data.wizard.mapper.toWizardList
import com.assignment.hometab.data.wizard.mapper.toWizardWithFavoriteList
import com.assignment.hometab.data.wizard.model.WizardsResponse
import com.assignment.hometab.data.wizard.repository.WizardRemoteRepository
import com.assignment.result.NetworkResult
import kotlinx.coroutines.flow.single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


private val SUCCESS_WIZARD_LIST_RESPONSE: List<WizardsResponse> =
    listOf(
        WizardsResponse()
    )


private val ERROR_WIZARD_LIST =
    NetworkException.ApiErrorException()

@RunWith(MockitoJUnitRunner::class)
class WizardRemoteRepositoryTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    private lateinit var sut: WizardRemoteRepository

    @Mock
    private lateinit var wizardsService: WizardsService

    @Before
    fun setUp() {
        sut = WizardRemoteRepository(wizardsService)
    }

    @Test
    fun `test getWizards when api success, getWizards should emit success data`() {
        testCoroutineRule.runTest {
            prepareSuccess()
            val result = sut.getWizards()
            assertEquals(
                ResultWrapper.Success(
                    SUCCESS_WIZARD_LIST_RESPONSE.toWizardList().toWizardWithFavoriteList()
                ), result.single()
            )
        }
    }

    @Test
    fun `test getWizards when api failure, getWizards should emit failure data`() {
        testCoroutineRule.runTest {
            prepareError()
            val result = sut.getWizards()
            assertEquals(ResultWrapper.Error(ERROR_WIZARD_LIST), result.single())
        }
    }

    private suspend fun prepareSuccess() {
        Mockito.`when`(wizardsService.getWizards())
            .thenReturn(NetworkResult.Success(SUCCESS_WIZARD_LIST_RESPONSE))
    }

    private suspend fun prepareError() {
        Mockito.`when`(wizardsService.getWizards())
            .thenReturn(NetworkResult.Error(ERROR_WIZARD_LIST))
    }
}