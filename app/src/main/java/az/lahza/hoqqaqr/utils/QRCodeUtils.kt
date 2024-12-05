package az.lahza.hoqqaqr.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter

fun generateQRCode(
    text: String,
    dotColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    qrSize: Int = 512,
    dotSize: Float = 4f,
    logo: Bitmap? = null
): Bitmap {
    require(text.isNotEmpty()) { "Text cannot be empty" }
    require(qrSize > 0) { "QR size must be greater than 0" }
    require(dotSize > 0) { "Dot size must be greater than 0" }

    try {
        // Generate QR code bit matrix
        val writer = QRCodeWriter()
        val bitMatrix: BitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, qrSize, qrSize)
        val width = bitMatrix.width
        val height = bitMatrix.height

        // Create bitmap and canvas for drawing
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        val canvas = Canvas(bmp)
        val paint = Paint().apply { isAntiAlias = true }

        // Fill background color
        canvas.drawColor(backgroundColor.toArgb())

        // Draw QR code dots
        drawQRCodeDots(
            bitMatrix = bitMatrix,
            width = width,
            height = height,
            paint = paint,
            canvas = canvas,
            dotColor = dotColor,
            dotSize = dotSize
        )

        logo?.let {
            drawLogo(
                logo = it,
                canvas = canvas,
                qrSize = qrSize
            )
        }

        return bmp
    } catch (e: Exception) {
        // Handle any errors during QR code generation or bitmap creation
        throw RuntimeException("Error generating QR code", e)
    }
}

private fun drawLogo(logo: Bitmap, canvas: Canvas, qrSize: Int) {
    val logoSize = qrSize / 7
    val left = (qrSize - logoSize) / 2
    val top = (qrSize - logoSize) / 2
    val right = left + logoSize
    val bottom = top + logoSize
    val logoRect = Rect(left, top, right, bottom)

    canvas.drawBitmap(logo, null, logoRect, null)
}

private fun drawQRCodeDots(
    bitMatrix: BitMatrix,
    width: Int,
    height: Int,
    paint: Paint,
    canvas: Canvas,
    dotColor: Color,
    dotSize: Float
) {
    for (x in 0 until width) {
        for (y in 0 until height) {
            if (bitMatrix.get(x, y)) {
                val centerX = (x).toFloat()
                val centerY = (y).toFloat()
                paint.color = dotColor.toArgb()
                canvas.drawCircle(centerX, centerY, dotSize, paint)
            }
        }
    }
}