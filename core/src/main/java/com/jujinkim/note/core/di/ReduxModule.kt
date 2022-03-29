package com.jujinkim.note.core.di

import com.jujinkim.note.core.AppState
import com.jujinkim.note.core.reducer.AppReducer
import com.jujinkim.note.data.repo.NoteRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.reduxkotlin.Store
import org.reduxkotlin.createThreadSafeStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ReduxModule {
    @Provides
    @Singleton
    fun provideStore(appReducer: AppReducer): Store<AppState> {
        return createThreadSafeStore(appReducer::rootReducer, AppState())
    }

    @Provides
    @Singleton
    fun provideReducer(noteRepo: NoteRepo): AppReducer {
        return AppReducer(noteRepo)
    }

}