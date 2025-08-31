package com.correxapp.domain.model

data class GradedSheet(
    val id: Long = 0,
    val examId: Long,
    val studentName: String,
    val correctionDate: Long,
    val score: Int,
    val correctAnswers: Int,
    val processingTimeMs: Long,
    val averageConfidence: Double,
    val individualAnswers: List<String>
)
