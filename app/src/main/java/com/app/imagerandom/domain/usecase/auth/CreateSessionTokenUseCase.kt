package com.app.imagerandom.domain.usecase.auth

import com.app.imagerandom.data.repository.AuthRepository
import javax.inject.Inject

class CreateSessionTokenUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(requestToken: String) = repo.createSessionToken(requestToken)
}