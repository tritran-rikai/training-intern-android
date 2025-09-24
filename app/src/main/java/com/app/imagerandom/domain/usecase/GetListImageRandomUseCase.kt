package com.app.imagerandom.domain.usecase

import com.app.imagerandom.data.repository.ImageRandomRepository
import com.app.imagerandom.domain.model.ImageRandom

class GetListImageRandomUseCase(private val repository: ImageRandomRepository) {
    suspend operator fun invoke(): List<ImageRandom> {
        return repository.getListImageRandom()

    }
}