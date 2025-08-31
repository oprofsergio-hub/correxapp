package com.correxapp.data.repository

import com.correxapp.data.database.dao.GradedSheetDao
import com.correxapp.data.database.entity.GradedSheetEntity
import com.correxapp.domain.model.GradedSheet
import com.correxapp.domain.repository.GradedSheetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GradedSheetRepositoryImpl(private val dao: GradedSheetDao) : GradedSheetRepository {
    override suspend fun saveGradedSheet(gs: GradedSheet): Long {
        return dao.insert(
            GradedSheetEntity(
                examId = gs.examId,
                studentName = gs.studentName,
                correctionDate = gs.correctionDate,
                score = gs.score,
                correctAnswers = gs.correctAnswers,
                processingTimeMs = gs.processingTimeMs,
                averageConfidence = gs.averageConfidence,
                answersJson = gs.individualAnswers.joinToString(",")
            )
        )
    }

    override fun observeByExam(examId: Long): Flow<List<GradedSheet>> =
        dao.observeByExam(examId).map { list ->
            list.map { e ->
                GradedSheet(
                    id = e.id,
                    examId = e.examId,
                    studentName = e.studentName,
                    correctionDate = e.correctionDate,
                    score = e.score,
                    correctAnswers = e.correctAnswers,
                    processingTimeMs = e.processingTimeMs,
                    averageConfidence = e.averageConfidence,
                    individualAnswers = e.answersJson.split(",").filter { it.isNotBlank() }
                )
            }
        }
}
