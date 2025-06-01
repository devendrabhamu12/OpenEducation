package com.customizeitlater.openeducation.data.network.responsemodel

import kotlinx.serialization.Serializable


@Serializable
data class RegisterResponse(
    val identity: String,
    val profileName: String,
    val email: String,
    val country: String,
    val phoneNumber: String,
    val createdAt: String,
    val bio: String,
    val role: String,
    val followerCount: Int,
    val followingCount: Int,
    val isDeleted: Boolean=false

)
