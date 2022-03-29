package com.jujinkim.note.model

data class Note(
    val id: String,
    val categoryId: String,
    val content: String,
    val generatedTime: Long,
    val expiredTime: Long = -1
) {
    fun isExpired() = expiredTime < System.currentTimeMillis()
}
