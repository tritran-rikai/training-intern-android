package com.app.imagerandom.data.repository

import com.app.imagerandom.domain.model.CreateSessionResponse
import com.app.imagerandom.domain.model.RequestTokenResponse
import com.app.imagerandom.domain.model.ValidateRequestTokenResponse

interface AuthRepository {
    suspend fun getRequestToken(apiKey: String): RequestTokenResponse
    suspend fun validateRequestToken(requestToken: String): ValidateRequestTokenResponse
    suspend fun createSessionToken(requestToken: String): CreateSessionResponse
}