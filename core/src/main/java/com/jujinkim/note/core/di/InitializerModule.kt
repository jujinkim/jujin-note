package com.jujinkim.note.core.di

import com.jujinkim.note.core.initializer.AppInitializer
import com.jujinkim.note.core.initializer.AppInitializerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InitializerModule {
    @Binds
    @Singleton
    abstract fun bindInitializer(initializerImpl: AppInitializerImpl) : AppInitializer

}