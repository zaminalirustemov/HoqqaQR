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

/**
 * Generates a QR code based on the provided text, with customizable dot color, background color, and size.
 * Optionally, you can add a logo to the QR code.
 *
 * @param text The text to encode in the QR code. It cannot be empty.
 * @param dotColor The color of the dots in the QR code. Defaults to black.
 * @param backgroundColor The background color of the QR code. Defaults to white.
 * @param qrSize The size of the QR code (width and height). Defaults to 512px.
 * @param dotSize The size of the individual dots in the QR code. Defaults to 4f (pixels).
 * @param logo An optional logo (Bitmap) to be placed in the center of the QR code. Defaults to null (no logo).
 *
 * @return A Bitmap representing the generated QR code.
 *
 * @throws IllegalArgumentException If the `text` is empty, or the `qrSize` or `dotSize` is less than or equal to 0.
 * @throws RuntimeException If an error occurs during QR code generation.
 */
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

/**
 * Draws the provided logo onto the center of the QR code.
 *
 * @param logo The logo bitmap to be drawn in the center of the QR code.
 * @param canvas The canvas to draw the logo onto.
 * @param qrSize The size of the QR code, used to calculate the logo's size and position.
 */
private fun drawLogo(logo: Bitmap, canvas: Canvas, qrSize: Int) {
    // Define the size of the logo as a fraction of the QR code size
    val logoSize = qrSize / 7
    val left = (qrSize - logoSize) / 2
    val top = (qrSize - logoSize) / 2
    val right = left + logoSize
    val bottom = top + logoSize
    val logoRect = Rect(left, top, right, bottom)

    // Draw the logo at the calculated position
    canvas.drawBitmap(logo, null, logoRect, null)
}

/**
 * Draws the dots of the QR code based on the bit matrix.
 *
 * This function iterates over the bit matrix, drawing a dot (circle) for each "1" value in the matrix.
 *
 * @param bitMatrix The bit matrix representing the QR code pattern.
 * @param width The width of the QR code (same as the bitMatrix width).
 * @param height The height of the QR code (same as the bitMatrix height).
 * @param paint The paint object used for drawing the dots.
 * @param canvas The canvas on which the QR code is drawn.
 * @param dotColor The color of the dots in the QR code.
 * @param dotSize The size of the individual dots.
 */
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