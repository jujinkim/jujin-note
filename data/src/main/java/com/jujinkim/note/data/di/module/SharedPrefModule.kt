package com.jujinkim.note.data.di.module

import android.content.Context
import com.jujinkim.note.data.AppSharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SharedPrefModule {
    @Provides
    fun provideSharedPref(@ApplicationContext context: Context) = AppSharedPref(context)
}