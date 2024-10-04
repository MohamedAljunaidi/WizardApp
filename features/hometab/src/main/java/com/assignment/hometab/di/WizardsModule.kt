package com.assignment.hometab.di

import com.assignment.caching.manager.CachingManager
import com.assignment.hometab.data.wizard.WizardsService
import com.assignment.hometab.data.wizard.repository.WizardLocalRepository
import com.assignment.hometab.data.wizard.repository.WizardRemoteRepository
import com.assignment.hometab.domain.wizard.repository.IWizardLocalRepository
import com.assignment.hometab.domain.wizard.repository.IWizardRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class WizardsModule {

    @Provides
    @ViewModelScoped
    fun getWizardRepository(wizardsService: WizardsService): IWizardRemoteRepository {
        return WizardRemoteRepository(wizardsService)
    }

    @Provides
    @ViewModelScoped
    fun getWizardsLocalRepository(cachingManager: CachingManager): IWizardLocalRepository {
        return WizardLocalRepository(cachingManager)
    }
}