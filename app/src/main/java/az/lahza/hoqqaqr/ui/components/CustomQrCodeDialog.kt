package az.lahza.hoqqaqr.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import az.lahza.hoqqaqr.R

/**
 * A composable function that displays a dialog containing a generated QR code,
 * with options to save the QR code to the gallery or share it.
 *
 * The dialog includes:
 * - A close button to dismiss the dialog.
 * - A title ("QR Code") displayed above the QR code.
 * - The QR code image, which is provided as a [Bitmap].
 * - Action buttons that allow saving the QR code to the gallery or sharing it.
 *
 * @param qrBitmap The generated QR code image as a [Bitmap] to be displayed in the dialog.
 * @param onDismiss Lambda function that is triggered when the dialog is dismissed.
 * @param onSaveToGallery Lambda function that is triggered when the "Save to Gallery" button is clicked.
 */
@Composable
fun CustomQrCodeDialog(
    qrBitmap: Bitmap?,
    onDismiss: () -> Unit,
    onSaveToGallery: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CloseButton { onDismiss() }
                    }

                    Text(
                        text = stringResource(id = R.string.qr_code),
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.manrope_bold))
                    )
                }

                qrBitmap?.asImageBitmap()?.let {
                    Image(
                        bitmap = it,
                        contentDescription = stringResource(id = R.string.qr_code),
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .padding(16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Buttons (Save to Gallery and Share)
                ActionButtons(
                    onSaveToGallery = onSaveToGallery,
                    qrBitmap = qrBitmap
                )
            }
        }
    }
}