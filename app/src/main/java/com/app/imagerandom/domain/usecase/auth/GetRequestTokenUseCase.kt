package com.app.imagerandom.domain.usecase.auth

import com.app.imagerandom.data.repository.AuthRepository
import javax.inject.Inject

class GetRequestTokenUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke() = repo.getRequestToken("Khong can api key dau hehe")
}