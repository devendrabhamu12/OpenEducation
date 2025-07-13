package com.customizeitlater.openeducation.data.network.requestmodel

import kotlinx.serialization.Serializable

@Serializable
data class QuestionDTO(
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val answer: Int,           // 1 to 4
    val explanation: String
)
