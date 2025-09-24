package com.app.imagerandom.data.repository

import com.app.imagerandom.domain.model.ImageRandom

interface ImageRandomRepository {
    suspend fun getListImageRandom(page: Int, perPage: Int): List<ImageRandom>
    suspend fun getImageRandom(): ImageRandom? // Added new function
}