package com.app.imagerandom.domain.usecase.theme

import com.app.imagerandom.data.repository.AppThemeRepository
import com.app.imagerandom.domain.model.AppTheme
import kotlinx.coroutines.flow.Flow


class GetThemeUseCase(private val repository: AppThemeRepository) {
    operator fun invoke(): Flow<AppTheme> = repository.getTheme()
}