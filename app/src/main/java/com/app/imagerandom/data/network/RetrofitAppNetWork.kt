package com.app.imagerandom.data.network

import com.app.imagerandom.domain.model.ImageRandom
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API declaration for ImageRandom Network API
 */
interface ImageRandomApiService {
    @GET(value = "/photos")
    suspend fun getImages(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("client_id") clientId: String // Added API Key
    ): List<ImageRandom>
}
