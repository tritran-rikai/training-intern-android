package com.app.imagerandom.domain.model

import com.google.gson.annotations.SerializedName

data class ValidateRequestTokenRequest(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("request_token") val requestToken: String
)

data class ValidateRequestTokenResponse(
    @SerializedName("success") val isSuccess: Boolean,
    @SerializedName("expires_at") val expiresAt: String,
    @SerializedName("request_token") val requestToken: String
)

data class CreateSessionRequest(
    @SerializedName("request_token") val requestToken: String
)

data class CreateSessionResponse(
    @SerializedName("success") val isSuccess: String,
    @SerializedName("session_id") val sessionId: String
)