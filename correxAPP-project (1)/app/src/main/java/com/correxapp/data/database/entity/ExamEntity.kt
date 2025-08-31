package com.correxapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exams")
data class ExamEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val totalQuestions: Int,
    val alternativesPerQuestion: Int,
    val answerKeyJson: String
)
