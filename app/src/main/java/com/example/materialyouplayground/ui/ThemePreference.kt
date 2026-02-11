package com.example.materialyouplayground.ui

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.materialyouplayground.ui.theme.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class ThemePreference(private val context: Context) {

    companion object {
        val THEME_KEY = intPreferencesKey("theme_choice")
    }

    val themeFlow: Flow<AppTheme> = context.dataStore.data
        .map { prefs ->
            when (prefs[THEME_KEY] ?: 2) {
                0 -> AppTheme.LIGHT
                1 -> AppTheme.DARK
                else -> AppTheme.SYSTEM
            }
        }

    suspend fun saveTheme(theme: AppTheme) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = when (theme) {
                AppTheme.LIGHT -> 0
                AppTheme.DARK -> 1
                AppTheme.SYSTEM -> 2
            }
        }
    }
}
