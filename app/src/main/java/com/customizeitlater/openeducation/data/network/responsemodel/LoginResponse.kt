package com.customizeitlater.openeducation.data.network.responsemodel

import kotlinx.serialization.Serializable


@Serializable
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
