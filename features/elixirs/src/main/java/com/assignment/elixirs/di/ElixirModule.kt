package com.assignment.elixirs.di

import com.assignment.caching.manager.CachingManager
import com.assignment.elixirs.data.ElixirService
import com.assignment.elixirs.data.repository.ElixirLocalRepository
import com.assignment.elixirs.data.repository.ElixirRemoteRepository
import com.assignment.elixirs.domain.repository.IElixirDetailsLocalRepository
import com.assignment.elixirs.domain.repository.IElixirDetailsRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ElixirModule {

    @Provides
    @ViewModelScoped
    fun getElixirRepository(elixirService: ElixirService): IElixirDetailsRemoteRepository {
        return ElixirRemoteRepository(elixirService)
    }

    @Provides
    @ViewModelScoped
    fun getElixirsLocalRepository(cachingManager: CachingManager): IElixirDetailsLocalRepository {
        return ElixirLocalRepository(cachingManager)
    }
}