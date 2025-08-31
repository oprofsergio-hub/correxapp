package com.correxapp.domain.repository

import com.correxapp.domain.model.Exam
import kotlinx.coroutines.flow.Flow

interface ExamRepository {
    suspend fun create(exam: Exam): Long
    suspend fun getExamById(id: Long): Exam?
    fun observeAll(): Flow<List<Exam>>
}
