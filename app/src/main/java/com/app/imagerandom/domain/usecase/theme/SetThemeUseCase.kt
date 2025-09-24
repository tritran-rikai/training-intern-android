package com.app.imagerandom.domain.usecase.theme

import com.app.imagerandom.data.repository.AppThemeRepository
import com.app.imagerandom.domain.model.AppTheme

class SetThemeUseCase(private val repository: AppThemeRepository) {
    suspend operator fun invoke(theme: AppTheme) {
        repository.setTheme(theme)
    }
}