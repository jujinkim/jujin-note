package com.jujinkim.note.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jujinkim.note.model.Category

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "icon") val icon: Int
)

fun CategoryEntity.toModel() = Category(id, title, icon)

fun Category.toEntity() = CategoryEntity(id, title, icon)