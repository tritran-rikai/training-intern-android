package com.app.imagerandom.domain.model

import com.google.gson.annotations.SerializedName

data class ValidateRequestTokenRequest(
    val username: String = "test_account_tritx",
    val password: String = "Abc@123",
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