package com.app.imagerandom.data.repository

import android.content.Context
import com.app.imagerandom.domain.model.AppTheme
import com.app.imagerandom.data.local.preferences.ThemePreferenceManager
import kotlinx.coroutines.flow.Flow

class AppThemeRepositoryImpl(
    private val context: Context
) : AppThemeRepository {
    override fun getTheme(): Flow<AppTheme> {
        return ThemePreferenceManager.getTheme(context)
    }

    override suspend fun setTheme(theme: AppTheme) {
        ThemePreferenceManager.setTheme(context, theme)
    }
}