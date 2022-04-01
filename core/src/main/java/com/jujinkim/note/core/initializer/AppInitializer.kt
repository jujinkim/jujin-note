package com.jujinkim.note.core.initializer

interface AppInitializer {
    fun isInitialized() : Boolean
    fun initialize()
}