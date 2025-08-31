package com.correxapp.domain.repository

import com.correxapp.domain.model.GradedSheet
import kotlinx.coroutines.flow.Flow

interface GradedSheetRepository {
    suspend fun saveGradedSheet(gs: GradedSheet): Long
    fun observeByExam(examId: Long): Flow<List<GradedSheet>>
}
