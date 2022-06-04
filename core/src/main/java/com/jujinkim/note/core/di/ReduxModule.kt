package com.jujinkim.note.core.di

import android.content.Context
import com.jujinkim.note.core.AppState
import com.jujinkim.note.core.DatabaseThunks
import com.jujinkim.note.core.reducer.AppReducer
import com.jujinkim.note.data.AppSharedPref
import com.jujinkim.note.data.repo.NoteRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideReducer(
        noteRepo: NoteRepo,
        appSharedPref: AppSharedPref,
        @ApplicationContext context: Context
    ): AppReducer {
        return AppReducer(noteRepo, appSharedPref, context)
    }

    @Provides
    @Singleton
    fun provideDatabaseThunks(noteRepo: NoteRepo): DatabaseThunks {
        return DatabaseThunks(noteRepo)
    }

}