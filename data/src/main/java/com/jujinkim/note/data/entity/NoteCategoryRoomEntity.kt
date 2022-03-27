package com.jujinkim.note.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteCategoryRoomEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String
)