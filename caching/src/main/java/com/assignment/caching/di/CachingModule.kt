package com.assignment.caching.di

import android.content.Context
import com.assignment.caching.manager.CachingManager
import com.assignment.caching.roomdb.common.DatabaseRoom
import com.assignment.caching.roomdb.common.RoomFactory
import com.assignment.caching.roomdb.common.RoomManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CachingModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): DatabaseRoom {
        return RoomFactory.createDatabaseRoom(appContext)
    }

    @Provides
    @Singleton
    fun provideCachingManager(
        roomManager: RoomManager
    ): CachingManager {
        return CachingManager(roomManager)
    }
}