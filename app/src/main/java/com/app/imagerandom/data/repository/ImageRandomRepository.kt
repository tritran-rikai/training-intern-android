package com.app.imagerandom.data.repository

import com.app.imagerandom.domain.model.ImageRandom

interface ImageRandomRepository {
    suspend fun getListImageRandom() : List<ImageRandom>
}