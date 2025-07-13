package com.customizeitlater.openeducation.data.network.responsemodel

import kotlinx.serialization.Serializable

@Serializable
data class AphorismApiResponse(
    val success: Boolean,
    val message: String,
    val items: List<AphorismDTO>,

    // Pagination metadata
    val page: Int,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int,
    val last: Boolean,

    val timestamp: String // Backend sends ISO-8601 datetime like "2025-07-05T14:30:00"
)
