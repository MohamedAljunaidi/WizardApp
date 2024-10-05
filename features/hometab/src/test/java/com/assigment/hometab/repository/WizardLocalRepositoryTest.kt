package com.assigment.hometab.repository

import com.assigment.hometab.common.TestCoroutineRule
import com.assignment.caching.manager.BaseManager
import com.assignment.caching.manager.CachingManager
import com.assignment.caching.manager.ProviderEnum
import com.assignment.caching.roomdb.features.wizard.entities.FavoriteEntity
import com.assignment.caching.roomdb.features.wizard.entities.WizardEntity
import com.assignment.caching.roomdb.features.wizard.entities.WizardWithFavoriteEntity
import com.assignment.core.model.ResultException
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.data.wizard.mapper.entityToWizardWithFavoriteList
import com.assignment.hometab.data.wizard.repository.WizardLocalRepository
import kotlinx.coroutines.flow.single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

private val SUCCESS_WIZARD_LIST_RESPONSE: List<WizardWithFavoriteEntity> =
    listOf(
        WizardWithFavoriteEntity(
            wizard = WizardEntity("id"),
            favorite = FavoriteEntity(wizardId = "wizardId")
        )
    )
private val ERROR_WIZARD_LIST =
    ResultException()


@RunWith(MockitoJUnitRunner::class)
class WizardLocalRepositoryTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    private lateinit var sut: WizardLocalRepository

    @Mock
    private lateinit var cachingManager: CachingManager
    @Mock
    private lateinit var mockBaseManager: BaseManager

    @Before
    fun setUp() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM))
            .thenReturn(mockBaseManager)
        sut = WizardLocalRepository(cachingManager)
    }

    @Test
    fun `test getWizards when cache success, getWizards should emit success data`() {
        testCoroutineRule.runTest {
            prepareSuccess()
            val result = sut.getWizardWithFavorite()
            Assert.assertEquals(
                ResultWrapper.Success(
                    SUCCESS_WIZARD_LIST_RESPONSE.entityToWizardWithFavoriteList()
                ), result.single()
            )
        }
    }

    @Test
    fun `test getWizards when cache failure, getWizards should emit failure data`() {
        testCoroutineRule.runTest {
            prepareError()
            val result = sut.getWizardWithFavorite()
            Assert.assertEquals(ResultWrapper.Error(ERROR_WIZARD_LIST), result.single())
        }
    }

    private suspend fun prepareSuccess() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM).getWizardWithFavorite())
            .thenReturn(ResultWrapper.Success(SUCCESS_WIZARD_LIST_RESPONSE))
    }

    private suspend fun prepareError() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM).getWizardWithFavorite())
            .thenReturn(ResultWrapper.Error(ERROR_WIZARD_LIST))
    }
}