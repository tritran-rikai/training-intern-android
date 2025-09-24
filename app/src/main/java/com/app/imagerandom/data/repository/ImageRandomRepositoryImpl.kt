package com.app.imagerandom.data.repository

import com.app.imagerandom.data.network.ImageRandomApiService
import com.app.imagerandom.domain.model.ImageRandom
import javax.inject.Inject

class ImageRandomRepositoryImpl @Inject constructor(
    private val apiService: ImageRandomApiService
) : ImageRandomRepository {

    // TODO: Replace with your actual Unsplash Access Key or manage it via a better mechanism
    private val UNSPLASH_ACCESS_KEY = "AOL3pLiMlF3khU3wmUqluy67092vEvIyrr02z85F3b0"

    override suspend fun getListImageRandom(page: Int, perPage: Int): List<ImageRandom> {
        return apiService.getImages(page = page, perPage = perPage, clientId = UNSPLASH_ACCESS_KEY)
    }

    override suspend fun getImageRandom(): ImageRandom? {
        // For now, let's assume this fetches a single random image, not a list.
        // If the API for a single random image also supports client_id, it should be added here.
        // This part is not directly related to pagination of the list.
        // Consider if the API endpoint for a single image is different.
        // For example, if it's /photos/random and returns a single ImageRandom object:
        // return apiService.getRandomImage(clientId = UNSPLASH_ACCESS_KEY) // Assuming such a method exists in ImageRandomApiService
        return null // Placeholder, as the focus is on getListImageRandom pagination
    }
}
