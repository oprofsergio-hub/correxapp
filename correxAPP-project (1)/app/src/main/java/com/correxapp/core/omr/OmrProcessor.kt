package com.correxapp.core.omr

import android.graphics.Bitmap
import com.correxapp.domain.model.OmrResult
import org.opencv.android.Utils
import org.opencv.core.*
import org.opencv.imgproc.Imgproc

class OmrProcessor {
    companion object {
        private const val SHEET_WIDTH = 800
        private const val SHEET_HEIGHT = 1100
        private const val BUBBLE_RADIUS_RATIO = 0.01
        private const val MARKING_THRESHOLD_RATIO = 0.4
    }

    fun processSheet(
        bitmap: Bitmap,
        totalQuestions: Int,
        alternativesPerQuestion: Int,
        answerKey: Map<Int, Int>
    ): OmrResult {
        val startTime = System.currentTimeMillis()

        val inputMat = Mat()
        Utils.bitmapToMat(bitmap, inputMat)

        // 1. Pre-processing
        val grayMat = Mat()
        Imgproc.cvtColor(inputMat, grayMat, Imgproc.COLOR_BGR2GRAY)
        val blurredMat = Mat()
        Imgproc.GaussianBlur(grayMat, blurredMat, Size(5.0, 5.0), 0.0)
        val threshMat = Mat()
        Imgproc.adaptiveThreshold(
            blurredMat, threshMat, 255.0,
            Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY_INV, 11, 2.0
        )

        // 2. Find sheet contour and apply perspective transform
        val warpedSheet = findAndWarpSheet(threshMat, grayMat) ?: return OmrResult.Error("Sheet not found")

        // 3. Analyze bubbles
        return analyzeBubbles(warpedSheet, totalQuestions, alternativesPerQuestion, answerKey, startTime)
    }

    private fun findAndWarpSheet(threshMat: Mat, grayMat: Mat): Mat? {
        val contours = mutableListOf<MatOfPoint>()
        val hierarchy = Mat()
        Imgproc.findContours(threshMat, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE)

        if (contours.isEmpty()) return null

        val sheetContour = contours.maxByOrNull { Imgproc.contourArea(it) } ?: return null

        val peri = Imgproc.arcLength(MatOfPoint2f(*sheetContour.toArray()), true)
        val approx = MatOfPoint2f()
        Imgproc.approxPolyDP(MatOfPoint2f(*sheetContour.toArray()), approx, 0.02 * peri, true)

        if (approx.rows() != 4) return null

        val points = approx.toArray().map { Point(it.x, it.y) }
        val orderedPoints = orderPoints(points)

        val srcPoints = MatOfPoint2f().apply { fromArray(*orderedPoints.toTypedArray()) }
        val dstPoints = MatOfPoint2f().apply {
            fromArray(
                Point(0.0, 0.0),
                Point(SHEET_WIDTH.toDouble() - 1, 0.0),
                Point(SHEET_WIDTH.toDouble() - 1, SHEET_HEIGHT.toDouble() - 1),
                Point(0.0, SHEET_HEIGHT.toDouble() - 1)
            )
        }

        val transformMatrix = Imgproc.getPerspectiveTransform(srcPoints, dstPoints)
        val warped = Mat()
        Imgproc.warpPerspective(grayMat, warped, transformMatrix, Size(SHEET_WIDTH.toDouble(), SHEET_HEIGHT.toDouble()))
        return warped
    }

    private fun orderPoints(points: List<Point>): List<Point> {
        val sortedBySum = points.sortedBy { it.x + it.y }
        val topLeft = sortedBySum.first()
        val bottomRight = sortedBySum.last()

        val sortedByDiff = points.sortedBy { it.y - it.x }
        val topRight = sortedByDiff.first()
        val bottomLeft = sortedByDiff.last()

        return listOf(topLeft, topRight, bottomRight, bottomLeft)
    }

    private fun analyzeBubbles(
        warpedSheet: Mat,
        totalQuestions: Int,
        alternativesPerQuestion: Int,
        answerKey: Map<Int, Int>,
        startTime: Long
    ): OmrResult {
        // Minimal placeholder so the project compiles;
        // a full implementation should compute filled bubbles and score.
        val processingTime = System.currentTimeMillis() - startTime
        return OmrResult.Error("OMR analysis not implemented yet (placeholder). Took ${processingTime}ms")
    }
}
