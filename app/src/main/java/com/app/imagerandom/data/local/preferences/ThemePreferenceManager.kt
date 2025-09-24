package com.app.imagerandom.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.app.imagerandom.domain.model.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object ThemePreferenceManager {
    private val Context.dataStore by preferencesDataStore(name = "theme_preferences")
    private val THEME_KEY = stringPreferencesKey("app_theme")

    suspend fun setTheme(context: Context, theme: AppTheme) {
        context.dataStore.edit { it[THEME_KEY] = theme.name }
    }

    fun getTheme(context: Context): Flow<AppTheme> {
        return context.dataStore.data.map {
            val themeName = it[THEME_KEY] ?: AppTheme.SYSTEM.name
            AppTheme.valueOf(themeName)
        }
    }
}