package com.customizeitlater.openeducation.ui.screens.aphorismcreate




data class CreateAphorismUiState(
    val title: String = "",
    val question: String = "",
    val option1: String = "",
    val option2: String = "",
    val option3: String = "",
    val option4: String = "",
    val correctAnswer: Int = 1,
    val explanation: String = "",
    val tags: String = ""
)
