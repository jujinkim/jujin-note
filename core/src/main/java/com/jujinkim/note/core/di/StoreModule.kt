package com.jujinkim.note.core.di

import com.jujinkim.note.core.AppState
import com.jujinkim.note.core.reducer.rootReducer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.reduxkotlin.Store
import org.reduxkotlin.createThreadSafeStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StoreModule {
    @Provides
    @Singleton
    fun provideStore(): Store<AppState> {
        return createThreadSafeStore(::rootReducer, AppState())
    }
}