package com.jujinkim.note.data.repo

import com.jujinkim.note.data.repo.datasource.DatabaseNoteDataSource
import com.jujinkim.note.model.Note
import com.jujinkim.note.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepoImpl @Inject constructor(
    private val databaseDataSource: DatabaseNoteDataSource
): NoteRepo {
    private var dataSource = databaseDataSource

    override suspend fun getNote(id: String) = withContext(Dispatchers.IO) {
        dataSource.getNote(id)
    }

    override suspend fun getNotes(catId: String) = withContext(Dispatchers.IO) {
        dataSource.getNotes(catId)
    }

    override suspend fun getAllNotes() = withContext(Dispatchers.IO) {
        dataSource.getAllNotes()
    }

    override suspend fun getCategory(catId: String) = withContext(Dispatchers.IO) {
        dataSource.getCategory(catId)
    }

    override suspend fun getCategories() = withContext(Dispatchers.IO) {
        dataSource.getCategories()
    }

    override suspend fun saveNote(note: Note, isNew: Boolean) = withContext(Dispatchers.IO) {
        dataSource.saveNote(note, isNew)
    }

    override suspend fun saveNotes(notes: List<Note>, isNew: Boolean) = withContext(Dispatchers.IO) {
        dataSource.saveNotes(notes, isNew)
    }

    override suspend fun saveCategory(category: Category, isNew: Boolean) = withContext(Dispatchers.IO) {
        dataSource.saveCategory(category, isNew)
    }

    override suspend fun saveCategories(categories: List<Category>, isNew: Boolean) = withContext(Dispatchers.IO) {
        dataSource.saveCategories(categories, isNew)
    }

    override suspend fun deleteNote(note: Note) = withContext(Dispatchers.IO) {
        dataSource.deleteNote(note)
    }

    override suspend fun deleteNote(id: String) = withContext(Dispatchers.IO) {
        dataSource.deleteNote(id)
    }

    override suspend fun deleteNotes(notes: List<Note>) = withContext(Dispatchers.IO) {
        dataSource.deleteNotes(notes)
    }

    override suspend fun deleteCategory(category: Category) = withContext(Dispatchers.IO) {
        dataSource.deleteCategory(category)
    }

    override suspend fun deleteCategory(catId: String) = withContext(Dispatchers.IO) {
        dataSource.deleteCategory(catId)
    }

    override suspend fun mirrorNotes(source: List<Note>) = withContext(Dispatchers.IO) {
        dataSource.deleteAllNotes()
        dataSource.saveNotes(source, true)
    }

    override suspend fun mirrorCategories(source: List<Category>) = withContext(Dispatchers.IO) {
        dataSource.deleteAllCategories()
        dataSource.saveCategories(source, true)
    }

    override fun getCurrentDataSource() = dataSource
}