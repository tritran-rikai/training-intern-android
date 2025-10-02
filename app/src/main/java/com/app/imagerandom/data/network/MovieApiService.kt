package com.app.imagerandom.data.network

import com.app.imagerandom.domain.model.CreateSessionRequest
import com.app.imagerandom.domain.model.CreateSessionResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.app.imagerandom.domain.model.RequestTokenResponse
import com.app.imagerandom.domain.model.ValidateRequestTokenRequest
import com.app.imagerandom.domain.model.ValidateRequestTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface MovieApiService {

    // Tao request token
    @GET("authentication/token/new")
    suspend fun getRequestToken(): RequestTokenResponse

    // Xac thuc request token voi username va password
    @POST("authentication/token/validate_with_login")
    suspend fun validateRequestToken(
        @Body body: ValidateRequestTokenRequest
    ): ValidateRequestTokenResponse

    // Tao session moi
    @POST("authentication/session/new")
    suspend fun createSession(
        @Body body: CreateSessionRequest
    ): CreateSessionResponse
}
