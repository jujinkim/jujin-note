package com.jujinkim.note.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jujinkim.note.model.NoteCategory

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String
)

fun CategoryEntity.toModel() = NoteCategory(id, title)

fun NoteCategory.toEntity() = CategoryEntity(id, title)