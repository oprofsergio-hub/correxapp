package com.correxapp.domain.usecase

import com.correxapp.domain.model.Exam
import com.correxapp.domain.repository.ExamRepository
import javax.inject.Inject

class CreateExamUseCase @Inject constructor(
    private val repo: ExamRepository
) {
    suspend operator fun invoke(exam: Exam): Long = repo.create(exam)
}
