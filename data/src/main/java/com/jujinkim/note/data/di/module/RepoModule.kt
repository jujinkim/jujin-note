package com.jujinkim.note.data.di.module

import com.jujinkim.note.data.repo.NoteRepo
import com.jujinkim.note.data.repo.NoteRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    abstract fun bindNoteRepo(repoImpl: NoteRepoImpl): NoteRepo
}