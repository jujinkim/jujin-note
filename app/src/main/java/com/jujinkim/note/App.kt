package com.jujinkim.note

import android.app.Application
import com.jujinkim.note.core.initializer.AppInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject lateinit var initializer: AppInitializer

    override fun onCreate() {
        super.onCreate()
        if (!initializer.isInitialized()) initializer.initialize()
    }
}