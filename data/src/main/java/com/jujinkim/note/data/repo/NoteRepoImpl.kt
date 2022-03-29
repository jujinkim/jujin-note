package com.jujinkim.note.data.repo

import com.jujinkim.note.data.repo.datasource.DatabaseNoteDataSource
import com.jujinkim.note.model.Note
import com.jujinkim.note.model.NoteCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepoImpl @Inject constructor(
    private val databaseDataSource: DatabaseNoteDataSource
): NoteRepo {
    private var dataSource = databaseDataSource

    override fun getNote(id: String): Note {
        return dataSource.getNote(id)
    }

    override fun getNotes(catId: String): List<Note> {
        return dataSource.getNotes(catId)
    }

    override fun getAllNotes(): List<Note> {
        return dataSource.getAllNotes()
    }

    override fun getCategory(catId: String): NoteCategory {
        return dataSource.getCategory(catId)
    }

    override fun getCategories(): List<NoteCategory> {
        return dataSource.getCategories()
    }

    override fun saveNote(note: Note, isNew: Boolean) {
        return dataSource.saveNote(note, isNew)
    }

    override fun saveNotes(notes: List<Note>, isNew: Boolean) {
        return dataSource.saveNotes(notes, isNew)
    }

    override fun saveCategory(category: NoteCategory, isNew: Boolean) {
        return dataSource.saveCategory(category, isNew)
    }

    override fun saveCategories(categories: List<NoteCategory>, isNew: Boolean) {
        return dataSource.saveCategories(categories, isNew)
    }

    override fun deleteNote(note: Note) {
        dataSource.deleteNote(note)
    }

    override fun deleteNote(id: String) {
        dataSource.deleteNote(id)
    }

    override fun deleteNotes(notes: List<Note>) {
        dataSource.deleteNotes(notes)
    }

    override fun deleteCategory(category: NoteCategory) {
        dataSource.deleteCategory(category)
    }

    override fun deleteCategory(catId: String) {
        dataSource.deleteCategory(catId)
    }

    override fun mirrorNotes(source: List<Note>) {
        dataSource.deleteAllNotes()
        dataSource.saveNotes(source, true)
    }

    override fun mirrorCategories(source: List<NoteCategory>) {
        dataSource.deleteAllCategories()
        dataSource.saveCategories(source, true)
    }

    override fun getCurrentDataSource() = dataSource
}