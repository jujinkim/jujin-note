package com.jujinkim.note.model

import java.math.BigInteger
import java.security.MessageDigest

data class Category(
    val id: String,
    val title: String,
    val icon: Int
) {
    companion object {
        fun new(title: String, icon: Int = 0) : Category {
            val md5 = MessageDigest.getInstance("MD5")
            val idInput = title + System.currentTimeMillis()
            val id = BigInteger(1, md5.digest(idInput.toByteArray())).toString(16).padStart(32, '0')
            return Category(id, title, icon)
        }
    }
}
