package com.jujinkim.note.core.reducer

import android.content.Context
import com.jujinkim.note.core.AppState
import com.jujinkim.note.model.Setting

object SettingReducers {
    private const val SETTING_PREF_NAME = "settingSharedPreference"
    private const val SETTING_PREF_KEY_EXPIRED_DAY = "settingKeyExpiredDay"

    fun loadSetting(state: AppState, context: Context): AppState {
        val pref = context.getSharedPreferences(SETTING_PREF_NAME, Context.MODE_PRIVATE)
        val expiredDay = pref.getInt(SETTING_PREF_KEY_EXPIRED_DAY, 7)

        val newSetting = Setting(
            expiredDay
        )

        return state.copy(setting = newSetting)
    }

    fun saveSetting(state: AppState, context: Context, setting: Setting): AppState {
        val pref = context.getSharedPreferences(SETTING_PREF_NAME, Context.MODE_PRIVATE)
        pref.edit().apply() {
            putInt(SETTING_PREF_KEY_EXPIRED_DAY, setting.defaultExpiredDay)
        }.run {
            apply()
        }

        return state.copy(setting = setting)
    }

    fun sync(state: AppState): AppState {
        TODO()
    }
}