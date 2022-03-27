package com.jujinkim.note.model

data class Note(
    val id: String,
    val content: String,
    val generatedTime: Long,
    val timeToLive: Long = -1
)
