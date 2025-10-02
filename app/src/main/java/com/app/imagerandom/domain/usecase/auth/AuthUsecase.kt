package com.app.imagerandom.domain.usecase.auth

import com.app.imagerandom.data.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend fun getRequestToken() = repo.getRequestToken()

    suspend fun validateRequestToken(username: String, password: String, requestToken: String) =
        repo.validateRequestToken(username, password, requestToken)

    suspend fun createSessionToken(requestToken: String) =
        repo.createSessionToken(requestToken)
}
