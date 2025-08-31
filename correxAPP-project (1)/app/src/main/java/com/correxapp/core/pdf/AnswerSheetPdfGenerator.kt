package com.correxapp.core.pdf

import android.content.Context
import android.graphics.*
import android.graphics.pdf.PdfDocument
import com.correxapp.domain.model.Exam
import com.correxapp.core.utils.QrCodeGenerator
import java.io.File

class AnswerSheetPdfGenerator(private val context: Context) {
    fun generate(exam: Exam, outFile: File) {
        val doc = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4-ish in points
        val page = doc.startPage(pageInfo)
        val c = page.canvas

        val paint = Paint().apply { color = Color.BLACK; textSize = 16f }
        c.drawText("correxAPP - Folha de Respostas", 40f, 40f, paint)
        c.drawText("Prova: ${exam.name}", 40f, 70f, paint)

        // Simple grid demo
        val startY = 120f
        var y = startY
        for (q in 1..exam.totalQuestions) {
            c.drawText("Q$q:", 40f, y, paint)
            y += 20f
            if (y > 780f) break
        }

        // QR code (just exam id as text)
        val qr = QrCodeGenerator.generate("${exam.id}", 200, 200)
        c.drawBitmap(qr, 360f, 40f, null)

        doc.finishPage(page)
        outFile.outputStream().use { doc.writeTo(it) }
        doc.close()
    }
}
