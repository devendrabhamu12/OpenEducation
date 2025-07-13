package com.customizeitlater.openeducation.data.network.requestmodel

import kotlinx.serialization.Serializable


@Serializable
data class SaveAphorismRequest(
    val title: String,
    val sageId: String,
    val question:QuestionDTO,
    val tagNames:Set<String>
)
