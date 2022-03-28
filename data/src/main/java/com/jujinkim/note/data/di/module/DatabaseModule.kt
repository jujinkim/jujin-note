package com.jujinkim.note.data.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jujinkim.note.data.AppDatabase
import com.jujinkim.note.data.repo.NoteRepo
import com.jujinkim.note.data.repo.NoteRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).build()
    }

    @Binds
    abstract fun bindNoteRepo(repoImpl: NoteRepoImpl): NoteRepo
}