package com.correxapp.core.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

object BitmapUtils {
    fun solid(width: Int, height: Int, color: Int = Color.LTGRAY): Bitmap {
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val c = Canvas(bmp)
        c.drawColor(color)
        val p = Paint().apply { this.color = Color.DKGRAY; textSize = 24f }
        c.drawText("Placeholder", 20f, height/2f, p)
        return bmp
    }
}
