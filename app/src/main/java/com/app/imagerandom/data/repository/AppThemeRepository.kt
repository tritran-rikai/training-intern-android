package com.app.imagerandom.data.repository

import com.app.imagerandom.domain.model.AppTheme
import kotlinx.coroutines.flow.Flow

interface AppThemeRepository {
    fun getTheme(): Flow<AppTheme>
    suspend fun setTheme(theme: AppTheme)
}