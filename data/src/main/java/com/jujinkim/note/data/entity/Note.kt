package com.jujinkim.note.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "category_id") val categoryId: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "generated_time") val generatedTime: Long,
    @ColumnInfo(name = "expired_time") val expiredTime: Long = -1
)
