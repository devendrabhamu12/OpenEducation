package com.customizeitlater.openeducation.data.network.responsemodel


import com.customizeitlater.openeducation.data.network.requestmodel.QuestionDTO
import kotlinx.serialization.Serializable

@Serializable
data class AphorismDTO(
    val postId: Long? = null,
    val title: String? = null,
    val authorName: String? = null,
    val tags: Set<String> = emptySet(),
    val question: QuestionDTO? = null,
    val upvoteCount: Int = 0,
    val downvoteCount: Int = 0,
    val createdAt: String? = null // ISO string from backend (e.g. "2025-07-05T14:30:00")
)
