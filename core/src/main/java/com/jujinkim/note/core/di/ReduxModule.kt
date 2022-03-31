package com.jujinkim.note.core.di

import com.jujinkim.note.core.AppState
import com.jujinkim.note.core.DatabaseThunks
import com.jujinkim.note.core.reducer.AppReducer
import com.jujinkim.note.data.repo.NoteRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.reduxkotlin.Store
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.createThreadSafeStore
import org.reduxkotlin.createThunkMiddleware
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ReduxModule {
    @Provides
    @Singleton
    fun provideStore(appReducer: AppReducer): Store<AppState> {
        return createThreadSafeStore(
            appReducer::rootReducer,
            AppState(),
            applyMiddleware(createThunkMiddleware())
        )
    }

    @Provides
    @Singleton
    fun provideReducer(noteRepo: NoteRepo): AppReducer {
        return AppReducer(noteRepo)
    }

    @Provides
    @Singleton
    fun provideDatabaseThunks(noteRepo: NoteRepo): DatabaseThunks {
        return DatabaseThunks(noteRepo)
    }

}