package com.app.imagerandom.data.repository

import com.app.imagerandom.domain.model.ImageRandom

class ImageRandomRepositoryImpl : ImageRandomRepository {
    override suspend fun getListImageRandom(): List<ImageRandom> {
        return emptyList()
    }
}