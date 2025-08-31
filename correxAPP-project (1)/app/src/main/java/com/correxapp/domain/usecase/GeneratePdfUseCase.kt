package com.correxapp.domain.usecase

import android.content.Context
import com.correxapp.core.pdf.AnswerSheetPdfGenerator
import com.correxapp.domain.model.Exam
import java.io.File
import javax.inject.Inject

class GeneratePdfUseCase @Inject constructor(private val context: Context) {
    operator fun invoke(exam: Exam, outFile: File) {
        AnswerSheetPdfGenerator(context).generate(exam, outFile)
    }
}
