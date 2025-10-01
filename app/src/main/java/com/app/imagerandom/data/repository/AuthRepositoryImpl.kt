package com.app.imagerandom.data.repository

import com.app.imagerandom.data.network.MovieApiService
import com.app.imagerandom.domain.model.CreateSessionRequest
import com.app.imagerandom.domain.model.CreateSessionResponse
import com.app.imagerandom.domain.model.RequestTokenResponse
import com.app.imagerandom.domain.model.ValidateRequestTokenRequest
import com.app.imagerandom.domain.model.ValidateRequestTokenResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService
) : AuthRepository {
    override suspend fun getRequestToken(apiKey: String): RequestTokenResponse {
        return apiService.getRequestToken(apiKey)
    }

    override suspend fun validateRequestToken(requestToken: String): ValidateRequestTokenResponse {
        val request = ValidateRequestTokenRequest(requestToken = requestToken)
        return apiService.validateRequestToken(request)
    }

    override suspend fun createSessionToken(requestToken: String): CreateSessionResponse {
        val request = CreateSessionRequest(requestToken = requestToken)
        return apiService.createSession(request)
    }
}