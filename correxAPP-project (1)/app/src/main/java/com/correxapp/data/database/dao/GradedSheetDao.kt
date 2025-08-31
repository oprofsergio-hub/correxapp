package com.correxapp.data.database.dao

import androidx.room.*
import com.correxapp.data.database.entity.GradedSheetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GradedSheetDao {
    @Insert
    suspend fun insert(gs: GradedSheetEntity): Long

    @Query("SELECT * FROM graded_sheets WHERE examId = :examId ORDER BY id DESC")
    fun observeByExam(examId: Long): Flow<List<GradedSheetEntity>>
}
