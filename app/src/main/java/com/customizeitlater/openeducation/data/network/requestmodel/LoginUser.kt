package com.customizeitlater.openeducation.data.network.requestmodel

import kotlinx.serialization.Serializable


@Serializable
data class LoginUser(
    val identity: String,
    val password: String
)