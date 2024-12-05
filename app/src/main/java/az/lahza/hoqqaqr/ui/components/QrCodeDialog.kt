package az.lahza.hoqqaqr.ui.components

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import az.lahza.hoqqaqr.R
import az.lahza.hoqqaqr.utils.saveBitmapToGallery

@Composable
fun QrCodeDialog(
    bitmap: Bitmap?,
    showQrCodeDialog: Boolean,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    if (showQrCodeDialog && bitmap != null) {
        CustomQrCodeDialog(
            qrBitmap = bitmap,
            onDismiss = onDismiss,
            onSaveToGallery = {
                // Try saving the bitmap to the gallery and respond based on the result
                val isSaved =
                    saveBitmapToGallery(context, bitmap, context.getString(R.string.my_qr_code))
                val message =
                    if (isSaved) R.string.qr_code_saved_to_gallery else R.string.qr_code_save_failed

                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        )
    }
}