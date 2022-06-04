package com.jujinkim.note.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppSharedPref @Inject constructor(
    @ApplicationContext val context: Context
) {
    private val prefKey = "JUJINNOTE_SHARED_KEY"

    val sharedPref by lazyOf(context.getSharedPreferences(prefKey, Context.MODE_PRIVATE))

    fun saveDraftNote(catId: String, text: String) {
        sharedPref.edit().putString("Draft_$catId", text).apply()
    }

    fun getDraftNote(catId: String) = sharedPref.getString(catId, "") ?: ""

    fun getAllDraftNotes() = sharedPref.all
        .filter { it.key.startsWith("Draft_") }
        .mapKeys { it.key.removePrefix("Draft_") } as Map<String, String>

}