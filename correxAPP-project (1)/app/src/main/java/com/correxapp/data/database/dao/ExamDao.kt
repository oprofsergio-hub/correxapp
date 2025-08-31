package com.correxapp.data.database.dao

import androidx.room.*
import com.correxapp.data.database.entity.ExamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExamDao {
    @Insert
    suspend fun insert(exam: ExamEntity): Long

    @Update
    suspend fun update(exam: ExamEntity)

    @Delete
    suspend fun delete(exam: ExamEntity)

    @Query("SELECT * FROM exams WHERE id = :id")
    suspend fun getById(id: Long): ExamEntity?

    @Query("SELECT * FROM exams ORDER BY id DESC")
    fun observeAll(): Flow<List<ExamEntity>>
}
