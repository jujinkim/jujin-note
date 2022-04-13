package com.jujinkim.note.model

import java.math.BigInteger
import java.security.MessageDigest

data class Note(
    val id: String,
    val categoryId: String,
    val content: String,
    val generatedTime: Long,
    val expiredTime: Long = -1
) {
    fun isExpired() = expiredTime >= 0 && (expiredTime + 86400000) < System.currentTimeMillis() // 86400000 = 1Day

    companion object {
        fun new(catId: String, content: String, icon: Int = 0, expiredTime: Long = -1) : Note {
            val md5 = MessageDigest.getInstance("MD5")
            val idInput = catId + content + System.currentTimeMillis()
            val id = BigInteger(1, md5.digest(idInput.toByteArray())).toString(16).padStart(32, '0')
            return Note(id, catId, content, System.currentTimeMillis(), expiredTime)
        }
    }
}
