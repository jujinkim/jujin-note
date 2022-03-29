package com.jujinkim.note.data.repo.datasource

import com.jujinkim.note.data.AppDatabase
import com.jujinkim.note.data.entity.toEntity
import com.jujinkim.note.data.entity.toModel
import com.jujinkim.note.model.Note
import com.jujinkim.note.model.Category
import javax.inject.Inject

class DatabaseNoteDataSource @Inject constructor(): NoteDataSource {
    @Inject
    lateinit var db: AppDatabase

    override fun getNote(id: String) = db.noteDao().get(id).toModel()
    override fun getNotes(catId: String) = db.noteDao().getAllByCategory(catId).map { it.toModel() }
    override fun getAllNotes(): List<Note> = db.noteDao().getAll().map { it.toModel() }
    override fun getCategory(catId: String) = db.categoryDao().get(catId).toModel()
    override fun getCategories() = db.categoryDao().getAll().map { it.toModel() }

    override fun saveNote(note: Note, isNew: Boolean) {
        // To reduce transactions, explicit that saving new note or modifying existed.
        if (isNew) {
            db.noteDao().insert(note.toEntity())
        } else {
            db.noteDao().update(note.toEntity())
        }
    }

    override fun saveNotes(notes: List<Note>, isNew: Boolean) {
        if (isNew) {
            db.noteDao().insertAll(*notes.map { it.toEntity() }.toTypedArray())
            // Note: * prefix (list to varargs spread operator) cost is very high in kotlin. 올ㅋ.
            // https://bladecoder.medium.com/exploring-kotlins-hidden-costs-part-2-324a4a50b70#da53
        } else {
            db.noteDao().updateAll(*notes.map { it.toEntity() }.toTypedArray())
        }
    }

    override fun saveCategory(category: Category, isNew: Boolean) {
        if (isNew) {
            db.categoryDao().insert(category.toEntity())
        } else {
            db.categoryDao().update(category.toEntity())
        }
    }

    override fun saveCategories(categories: List<Category>, isNew: Boolean) {
        if (isNew) {
            db.categoryDao().insertAll(*categories.map { it.toEntity() }.toTypedArray())
        } else {
            db.categoryDao().updateAll(*categories.map { it.toEntity() }.toTypedArray())
        }
    }

    override fun deleteNote(note: Note) {
        db.noteDao().delete(note.toEntity())
    }

    override fun deleteNote(id: String) {
        db.noteDao().delete(id)
    }

    override fun deleteNotes(notes: List<Note>) {
        db.noteDao().deleteAll(*notes.map { it.toEntity() }.toTypedArray())
    }

    override fun deleteCategory(category: Category) {
        db.categoryDao().delete(category.toEntity())
    }

    override fun deleteCategory(catId: String) {
        db.categoryDao().delete(catId)
    }

    override fun deleteAllNotes() {
        db.noteDao().clearTable()
    }

    override fun deleteAllCategories() {
        db.categoryDao().clearTable()
    }
}