package com.correxapp.domain.model

sealed class OmrResult {
    data class Success(
        val score: Int,
        val correctCount: Int,
        val answers: List<String>,
        val processingTimeMs: Long,
        val averageConfidence: Double
    ) : OmrResult()
    data class Error(val message: String) : OmrResult()
}
