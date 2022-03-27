package com.jujinkim.note.data.dao

import androidx.room.*
import com.jujinkim.note.data.entity.Category

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: Category)

    @Insert
    fun insertAll(vararg category: Category)

    @Delete
    fun delete(category: Category)

    @Delete
    fun deleteAll(vararg category: Category)

    @Update
    fun update(category: Category)

    @Update
    fun updateAll(vararg category: Category)

    @Query("SELECT * FROM Category WHERE id = :catId")
    fun get(catId: String): Category

    @Query("SELECT * FROM Category")
    fun getAll(): List<Category>

}