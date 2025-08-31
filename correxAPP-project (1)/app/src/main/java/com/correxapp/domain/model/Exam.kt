package com.correxapp.domain.model

data class Exam(
    val id: Long = 0,
    val name: String,
    val totalQuestions: Int,
    val alternativesPerQuestion: Int,
    val answerKey: Map<Int, Int>
)
