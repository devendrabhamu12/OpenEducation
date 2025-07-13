package com.customizeitlater.openeducation.data.network.requestmodel

import kotlinx.serialization.Serializable


@Serializable
data class RefreshTokenRequest (val refreshToken: String)