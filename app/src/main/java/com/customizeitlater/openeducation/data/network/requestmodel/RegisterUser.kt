package com.customizeitlater.openeducation.data.network.requestmodel

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUser(
    val identity: String,
    val profileName: String,
    val password: String,
    val email: String,
    val country: String,
    val phoneNumber: String,
    val bio: String
)