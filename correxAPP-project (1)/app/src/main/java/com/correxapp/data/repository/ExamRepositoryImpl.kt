package com.correxapp.data.repository

import com.correxapp.data.database.dao.ExamDao
import com.correxapp.data.database.entity.ExamEntity
import com.correxapp.domain.model.Exam
import com.correxapp.domain.repository.ExamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExamRepositoryImpl(private val dao: ExamDao) : ExamRepository {
    override suspend fun create(exam: Exam): Long {
        return dao.insert(ExamEntity(
            name = exam.name,
            totalQuestions = exam.totalQuestions,
            alternativesPerQuestion = exam.alternativesPerQuestion,
            answerKeyJson = exam.answerKey.entries.joinToString(";") { "${it.key}:${it.value}" }
        ))
    }

    override suspend fun getExamById(id: Long): Exam? {
        val e = dao.getById(id) ?: return null
        val map = e.answerKeyJson.split(";").mapNotNull {
            val p = it.split(":")
            if (p.size == 2) p[0].toIntOrNull()?.let { k -> p[1].toIntOrNull()?.let { v -> k to v } } else null
        }.toMap()
        return Exam(
            id = e.id,
            name = e.name,
            totalQuestions = e.totalQuestions,
            alternativesPerQuestion = e.alternativesPerQuestion,
            answerKey = map
        )
    }

    override fun observeAll(): Flow<List<Exam>> =
        dao.observeAll().map { list ->
            list.map { e ->
                Exam(
                    id = e.id,
                    name = e.name,
                    totalQuestions = e.totalQuestions,
                    alternativesPerQuestion = e.alternativesPerQuestion,
                    answerKey = e.answerKeyJson.split(";").mapNotNull {
                        val p = it.split(":")
                        if (p.size == 2) p[0].toIntOrNull()?.let { k -> p[1].toIntOrNull()?.let { v -> k to v } } else null
                    }.toMap()
                )
            }
        }
}
