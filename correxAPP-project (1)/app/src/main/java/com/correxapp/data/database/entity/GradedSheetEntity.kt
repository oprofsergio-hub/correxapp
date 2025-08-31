package com.correxapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "graded_sheets")
data class GradedSheetEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val examId: Long,
    val studentName: String,
    val correctionDate: Long,
    val score: Int,
    val correctAnswers: Int,
    val processingTimeMs: Long,
    val averageConfidence: Double,
    val answersJson: String
)
