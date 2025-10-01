package com.app.imagerandom.domain.model

import com.google.gson.annotations.SerializedName

data class RequestTokenResponse(
    val success: Boolean,
    @SerializedName("expires_at") val expiresAt: String,
    @SerializedName("request_token") val requestToken: String
)