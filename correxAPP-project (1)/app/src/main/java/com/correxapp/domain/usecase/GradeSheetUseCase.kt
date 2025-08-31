package com.correxapp.domain.usecase

import android.graphics.Bitmap
import com.correxapp.core.omr.OmrProcessor
import com.correxapp.domain.model.GradedSheet
import com.correxapp.domain.model.OmrResult
import com.correxapp.domain.repository.ExamRepository
import com.correxapp.domain.repository.GradedSheetRepository
import javax.inject.Inject

class GradeSheetUseCase @Inject constructor(
    private val examRepository: ExamRepository,
    private val gradedSheetRepository: GradedSheetRepository,
    private val omrProcessor: OmrProcessor
) {
    suspend operator fun invoke(examId: Long, studentName: String, sheetBitmap: Bitmap): Result<GradedSheet> {
        return try {
            val exam = examRepository.getExamById(examId) ?: return Result.failure(Exception("Exam not found"))
            val answerKey = exam.answerKey

            when (val omrResult = omrProcessor.processSheet(
                sheetBitmap,
                exam.totalQuestions,
                exam.alternativesPerQuestion,
                answerKey
            )) {
                is OmrResult.Success -> {
                    val gradedSheet = GradedSheet(
                        id = 0,
                        examId = examId,
                        studentName = studentName,
                        correctionDate = System.currentTimeMillis(),
                        score = omrResult.score,
                        correctAnswers = omrResult.correctCount,
                        processingTimeMs = omrResult.processingTimeMs,
                        averageConfidence = omrResult.averageConfidence,
                        individualAnswers = omrResult.answers
                    )
                    val savedId = gradedSheetRepository.saveGradedSheet(gradedSheet)
                    Result.success(gradedSheet.copy(id = savedId))
                }
                is OmrResult.Error -> Result.failure(Exception(omrResult.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
