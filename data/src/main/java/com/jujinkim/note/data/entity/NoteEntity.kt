package com.jujinkim.note.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jujinkim.note.model.Note

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "category_id") val categoryId: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "generated_time") val generatedTime: Long,
    @ColumnInfo(name = "expired_time") val expiredTime: Long = -1
)

fun NoteEntity.toModel() = Note(id, categoryId, content, generatedTime, expiredTime)

fun Note.toEntity() = NoteEntity(id, categoryId, content, generatedTime, expiredTime)
