package com.jujinkim.note.data.dao

import androidx.room.*
import com.jujinkim.note.data.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryEntity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg categoryEntity: CategoryEntity)

    @Delete
    fun delete(categoryEntity: CategoryEntity)

    @Delete
    fun deleteAll(vararg categoryEntity: CategoryEntity)

    @Update
    fun update(categoryEntity: CategoryEntity)

    @Update
    fun updateAll(vararg categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category WHERE id = :catId")
    fun get(catId: String): CategoryEntity

    @Query("SELECT * FROM category")
    fun getAll(): List<CategoryEntity>

}